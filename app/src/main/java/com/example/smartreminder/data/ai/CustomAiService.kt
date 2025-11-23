package com.example.smartreminder.data.ai

import android.util.Log
import com.example.smartreminder.domain.model.AiConfig
import com.example.smartreminder.domain.model.AiParseResult
import com.example.smartreminder.domain.model.AiTimeSuggestion
import com.example.smartreminder.domain.model.RepeatType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.util.concurrent.TimeUnit

/**
 * 自定义AI服务实现
 * 用于支持其他兼容OpenAI格式的AI服务
 */
class CustomAiService(private val config: AiConfig) : AiService {

    private val client = OkHttpClient.Builder()
        .connectTimeout(config.timeoutMs.toLong(), TimeUnit.MILLISECONDS)
        .readTimeout(config.timeoutMs.toLong(), TimeUnit.MILLISECONDS)
        .writeTimeout(config.timeoutMs.toLong(), TimeUnit.MILLISECONDS)
        .build()

    private val baseUrl = config.baseUrl ?: "https://api.openai.com/v1"
    private val TAG = "CustomAiService"

    override suspend fun parseReminderText(text: String): Result<AiParseResult> {
        return withContext(Dispatchers.IO) {
            try {
                val prompt = PromptTemplate.createParsePrompt(text)
                val response = makeApiCall(prompt)

                if (response.isSuccess) {
                    val parsed = parseResponse(response.getOrThrow())
                    Result.success(parsed)
                } else {
                    Result.failure(Exception("API call failed: ${response.exceptionOrNull()?.message}"))
                }
            } catch (e: Exception) {
                Log.e(TAG, "Failed to parse reminder text", e)
                Result.failure(e)
            }
        }
    }

    override suspend fun suggestReminderTime(title: String, description: String?): Result<List<AiTimeSuggestion>> {
        return withContext(Dispatchers.IO) {
            try {
                val prompt = PromptTemplate.createTimeSuggestionPrompt(title, description ?: "")
                val response = makeApiCall(prompt)

                if (response.isSuccess) {
                    val suggestions = parseTimeSuggestions(response.getOrThrow())
                    Result.success(suggestions)
                } else {
                    Result.failure(Exception("API call failed: ${response.exceptionOrNull()?.message}"))
                }
            } catch (e: Exception) {
                Log.e(TAG, "Failed to suggest reminder time", e)
                Result.failure(e)
            }
        }
    }

    override suspend fun isAvailable(): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                // 简单检查API密钥是否有效
                val request = Request.Builder()
                    .url("$baseUrl/models")
                    .addHeader("Authorization", "Bearer ${config.apiKey}")
                    .build()

                client.newCall(request).execute().use { response ->
                    response.isSuccessful
                }
            } catch (e: Exception) {
                Log.e(TAG, "AI service availability check failed", e)
                false
            }
        }
    }

    override fun getConfig(): AiConfig = config

    private suspend fun makeApiCall(prompt: String): Result<String> {
        return withContext(Dispatchers.IO) {
            try {
                val jsonBody = createRequestBody(prompt)
                val request = Request.Builder()
                    .url("$baseUrl/chat/completions")
                    .addHeader("Authorization", "Bearer ${config.apiKey}")
                    .addHeader("Content-Type", "application/json")
                    .post(jsonBody)
                    .build()

                Log.d(TAG, "Making HTTP request to Custom AI API")
                client.newCall(request).execute().use { response ->
                    if (response.isSuccessful) {
                        val responseBody = response.body?.string()
                        if (responseBody != null) {
                            Result.success(responseBody)
                        } else {
                            Result.failure(IOException("Empty response body"))
                        }
                    } else {
                        val errorBody = response.body?.string()
                        Result.failure(IOException("HTTP ${response.code}: ${response.message}"))
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "API call failed", e)
                Result.failure(e)
            }
        }
    }

    private fun createRequestBody(prompt: String): okhttp3.RequestBody {
        Log.d(TAG, "Creating request body for Custom AI API")
        val json = JSONObject().apply {
            put("model", config.modelName)
            put("messages", JSONArray().apply {
                put(JSONObject().apply {
                    put("role", "user")
                    put("content", prompt)
                })
            })
            put("max_tokens", 500)
            put("temperature", 0.3)
        }
        Log.d(TAG, "Request body created. Model: ${config.modelName}, Max tokens: 500, Temperature: 0.3")
        return json.toString().toRequestBody("application/json".toMediaType())
    }

    private fun createParsePrompt(text: String): String {
        return PromptTemplate.createParsePrompt(text)
    }

    private fun createTimeSuggestionPrompt(title: String, description: String): String {
        return PromptTemplate.createTimeSuggestionPrompt(title, description)
    }

    private fun parseResponse(responseBody: String): AiParseResult {
        try {
            Log.d(TAG, "Parsing Custom AI response")
            val json = JSONObject(responseBody)
            val choices = json.getJSONArray("choices")
            if (choices.length() > 0) {
                val message = choices.getJSONObject(0).getJSONObject("message")
                val content = message.getString("content")
                Log.d(TAG, "Response content: $content")

                // 尝试清理JSON响应
                val cleanContent = content.trim()
                    .removePrefix("```json")
                    .removeSuffix("```")
                    .removePrefix("```")

                val resultJson = JSONObject(cleanContent)

                val title = resultJson.getString("title")
                val description = resultJson.optString("description", null).takeIf { it.isNotEmpty() }

                // 解析时间
                val timeStr = resultJson.optString("time", "").takeIf { it.isNotEmpty() }
                val dateStr = resultJson.optString("date", null)?.takeIf { it != "null" && it.isNotEmpty() }

                val scheduledTime = if (!timeStr.isNullOrEmpty()) {
                    val localTime = LocalTime.parse(timeStr)
                    if (dateStr.isNullOrEmpty() || dateStr == "今天") {
                        // 如果没有指定日期或日期为"今天"，使用今天的日期
                        val localDateTime = LocalDateTime.of(LocalDate.now(), localTime)
                        localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
                    } else {
                        // 如果指定了具体日期
                        val localDate = LocalDate.parse(dateStr)
                        val localDateTime = LocalDateTime.of(localDate, localTime)
                        localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
                    }
                } else {
                    resultJson.optLong("scheduledTime", -1).takeIf { it != -1L }
                }

                // 解析重复类型
                val recurrenceRule = resultJson.getString("recurrence_rule")
                val repeatType = try {
                    when (recurrenceRule.uppercase()) {
                        "DAILY" -> RepeatType.DAILY
                        "WEEKLY" -> RepeatType.WEEKLY
                        "MONTHLY" -> RepeatType.MONTHLY
                        "YEARLY" -> RepeatType.YEARLY
                        else -> RepeatType.NONE
                    }
                } catch (e: IllegalArgumentException) {
                    Log.w(TAG, "Invalid repeat type from AI: $recurrenceRule, defaulting to NONE")
                    RepeatType.NONE
                }

                // 解析每月重复的两种方式
                val monthlyWeek = if (resultJson.has("monthly_week") && !resultJson.isNull("monthly_week")) {
                    resultJson.getInt("monthly_week")
                } else {
                    null
                }

                val monthlyWeekDays = if (resultJson.has("monthly_weekday")) {
                    val monthlyWeekdayArray = resultJson.getJSONArray("monthly_weekday")
                    val monthlyWeekDaysSet = mutableSetOf<Int>()
                    for (i in 0 until monthlyWeekdayArray.length()) {
                        monthlyWeekDaysSet.add(monthlyWeekdayArray.getInt(i))
                    }
                    monthlyWeekDaysSet
                } else {
                    emptySet()
                }

                val result = AiParseResult(
                    title = title,
                    description = description,
                    scheduledTime = scheduledTime,
                    repeatType = repeatType,
                    monthDays = if (resultJson.has("monthdays")) {
                        val monthdaysArray = resultJson.getJSONArray("monthdays")
                        val monthDaysSet = mutableSetOf<Int>()
                        for (i in 0 until monthdaysArray.length()) {
                            monthDaysSet.add(monthdaysArray.getInt(i))
                        }
                        monthDaysSet
                    } else {
                        emptySet()
                    },
                    weekDays = if (resultJson.has("weekdays")) {
                        val weekdaysArray = resultJson.getJSONArray("weekdays")
                        val weekDaysSet = mutableSetOf<Int>()
                        for (i in 0 until weekdaysArray.length()) {
                            weekDaysSet.add(weekdaysArray.getInt(i))
                        }
                        weekDaysSet
                    } else {
                        emptySet()
                    },
                    monthlyWeek = monthlyWeek,
                    monthlyWeekDays = monthlyWeekDays
                )
                Log.d(TAG, "Successfully parsed result from Custom AI response - Title: $title, " +
                        "Description: $description, Time: $scheduledTime, Repeat: $repeatType")
                return result
            }
        } catch (e: Exception) {
            Log.e(TAG, "Failed to parse API response", e)
        }
        // 返回默认结果
        return AiParseResult(
            title = "解析失败",
            description = null,
            scheduledTime = null,
            repeatType = RepeatType.NONE
        )
    }

    private fun parseTimeSuggestions(responseBody: String): List<AiTimeSuggestion> {
        try {
            Log.d(TAG, "Parsing time suggestions from Custom AI response")
            val json = JSONObject(responseBody)
            val choices = json.getJSONArray("choices")
            if (choices.length() > 0) {
                val message = choices.getJSONObject(0).getJSONObject("message")
                val content = message.getString("content")
                Log.d(TAG, "Response content: $content")

                // 尝试清理JSON响应
                val cleanContent = content.trim()
                    .removePrefix("```json")
                    .removeSuffix("```")
                    .removePrefix("```")

                val suggestionsJson = JSONArray(cleanContent)

                val suggestions = mutableListOf<AiTimeSuggestion>()
                for (i in 0 until suggestionsJson.length()) {
                    val suggestionJson = suggestionsJson.getJSONObject(i)
                    val suggestedTime = suggestionJson.getLong("suggestedTime")
                    val reason = suggestionJson.getString("reason")

                    suggestions.add(AiTimeSuggestion(suggestedTime, reason))
                }
                Log.d(TAG, "Successfully parsed ${suggestions.size} time suggestions")
                return suggestions
            }
        } catch (e: Exception) {
            Log.e(TAG, "Failed to parse time suggestions", e)
        }
        return emptyList()
    }
}