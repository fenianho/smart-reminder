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
import okhttp3.RequestBody
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
 * DeepSeek AI服务实现
 */
class DeepSeekAiService(private val config: AiConfig) : AiService {

    private val client = OkHttpClient.Builder()
        .connectTimeout(config.timeoutMs.toLong(), TimeUnit.MILLISECONDS)
        .readTimeout(config.timeoutMs.toLong(), TimeUnit.MILLISECONDS)
        .writeTimeout(config.timeoutMs.toLong(), TimeUnit.MILLISECONDS)
        .build()

    private val baseUrl = config.baseUrl ?: "https://api.deepseek.com/v1"
    private val TAG = "DeepSeekAiService"

    override suspend fun parseReminderText(text: String): Result<AiParseResult> {
        Log.d(TAG, "Starting to parse reminder text with DeepSeek: $text")
        val startTime = System.currentTimeMillis()
        
        return withContext(Dispatchers.IO) {
            try {
                val prompt = PromptTemplate.createParsePrompt(text)
                Log.d(TAG, "Sending request to DeepSeek API for parsing")
                val response = makeApiCall(prompt)
                val duration = System.currentTimeMillis() - startTime

                if (response.isSuccess) {
                    Log.d(TAG, "DeepSeek API call successful. Duration: ${duration}ms")
                    val parsed = parseResponse(response.getOrThrow())
                    Log.d(TAG, "Successfully parsed response from DeepSeek - Title: ${parsed.title}, " +
                            "Description: ${parsed.description}, Time: ${parsed.scheduledTime}")
                    Result.success(parsed)
                } else {
                    Log.w(TAG, "DeepSeek API call failed. Duration: ${duration}ms")
                    Result.failure(Exception("API call failed: ${response.exceptionOrNull()?.message}"))
                }
            } catch (e: Exception) {
                val duration = System.currentTimeMillis() - startTime
                Log.e(TAG, "Failed to parse reminder text. Duration: ${duration}ms", e)
                Result.failure(e)
            }
        }
    }

    override suspend fun suggestReminderTime(title: String, description: String?): Result<List<AiTimeSuggestion>> {
        Log.d(TAG, "Starting to suggest reminder time with DeepSeek. Title: $title")
        val startTime = System.currentTimeMillis()
        
        return withContext(Dispatchers.IO) {
            try {
                val prompt = PromptTemplate.createTimeSuggestionPrompt(title, description ?: "")
                Log.d(TAG, "Sending request to DeepSeek API for time suggestion")
                val response = makeApiCall(prompt)
                val duration = System.currentTimeMillis() - startTime

                if (response.isSuccess) {
                    Log.d(TAG, "DeepSeek API call successful. Duration: ${duration}ms")
                    val suggestions = parseTimeSuggestions(response.getOrThrow())
                    Log.d(TAG, "Successfully parsed time suggestions from DeepSeek. Count: ${suggestions.size}")
                    suggestions.forEachIndexed { index, suggestion ->
                        Log.d(TAG, "Suggestion $index - Time: ${suggestion.suggestedTime}, Reason: ${suggestion.reason}")
                    }
                    Result.success(suggestions)
                } else {
                    Log.w(TAG, "DeepSeek API call failed. Duration: ${duration}ms")
                    Result.failure(Exception("API call failed: ${response.exceptionOrNull()?.message}"))
                }
            } catch (e: Exception) {
                val duration = System.currentTimeMillis() - startTime
                Log.e(TAG, "Failed to suggest reminder time. Duration: ${duration}ms", e)
                Result.failure(e)
            }
        }
    }

    override suspend fun isAvailable(): Boolean {
        Log.d(TAG, "Checking DeepSeek service availability")
        return withContext(Dispatchers.IO) {
            try {
                // 简单检查API密钥是否有效
                val request = Request.Builder()
                    .url("$baseUrl/models")
                    .addHeader("Authorization", "Bearer ${config.apiKey}")
                    .build()

                val startTime = System.currentTimeMillis()
                client.newCall(request).execute().use { response ->
                    val duration = System.currentTimeMillis() - startTime
                    val isAvailable = response.isSuccessful
                    Log.d(TAG, "DeepSeek availability check result: $isAvailable. Duration: ${duration}ms. " +
                            "HTTP code: ${response.code}")
                    isAvailable
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

                Log.d(TAG, "Making HTTP request to DeepSeek API")
                Log.d(TAG, "Request URL: $baseUrl/chat/completions")
                Log.d(TAG, "Request headers: Authorization=Bearer ${config.apiKey.maskApiKey()}, Content-Type=application/json")
                Log.d(TAG, "Request body: ${jsonBody.toString().trim()}")
                
                val startTime = System.currentTimeMillis()
                client.newCall(request).execute().use { response ->
                    val duration = System.currentTimeMillis() - startTime
                    if (response.isSuccessful) {
                        val responseBody = response.body?.string()
                        if (responseBody != null) {
                            Log.d(TAG, "HTTP request successful. Duration: ${duration}ms. Response size: ${responseBody.length} chars")
                            Log.d(TAG, "Response body: ${responseBody.take(1000)}${if (responseBody.length > 1000) "..." else ""}")
                            Result.success(responseBody)
                        } else {
                            Log.w(TAG, "Empty response body. Duration: ${duration}ms")
                            Result.failure(IOException("Empty response body"))
                        }
                    } else {
                        val errorBody = response.body?.string()
                        Log.w(TAG, "HTTP request failed. Duration: ${duration}ms. " +
                                "Code: ${response.code}, Message: ${response.message}, Error: $errorBody")
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
        Log.d(TAG, "Creating request body for DeepSeek API")
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
        val prompt = PromptTemplate.createParsePrompt(text)
        
        Log.d(TAG, "Created parse prompt for text: $text")
        Log.d(TAG, "Parse prompt content:\n$prompt")
        return prompt
    }

    private fun createTimeSuggestionPrompt(title: String, description: String): String {
        val prompt = PromptTemplate.createTimeSuggestionPrompt(title, description)
        
        Log.d(TAG, "Created time suggestion prompt for title: $title, description: $description")
        Log.d(TAG, "Time suggestion prompt content:\n$prompt")
        return prompt
    }

    private fun parseResponse(responseBody: String): AiParseResult {
        try {
            Log.d(TAG, "Parsing DeepSeek response")
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
                Log.d(TAG, "Successfully parsed result from DeepSeek response - Title: $title, " +
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
            Log.d(TAG, "Parsing time suggestions from DeepSeek response")
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

// 扩展函数用于隐藏API密钥
private fun String.maskApiKey(): String {
    return if (this.length > 6) {
        "${this.substring(0, 3)}...${this.substring(this.length - 3)}"
    } else {
        "***"
    }
}
