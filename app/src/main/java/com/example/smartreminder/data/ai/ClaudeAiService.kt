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
 * Claude AI服务实现
 */
class ClaudeAiService(private val config: AiConfig) : AiService {

    private val client = OkHttpClient.Builder()
        .connectTimeout(config.timeoutMs.toLong(), TimeUnit.MILLISECONDS)
        .readTimeout(config.timeoutMs.toLong(), TimeUnit.MILLISECONDS)
        .writeTimeout(config.timeoutMs.toLong(), TimeUnit.MILLISECONDS)
        .build()

    private val baseUrl = config.baseUrl ?: "https://api.anthropic.com/v1"
    private val TAG = "ClaudeAiService"

    override suspend fun parseReminderText(text: String): Result<AiParseResult> {
        Log.d(TAG, "Starting to parse reminder text with Claude: $text")
        val startTime = System.currentTimeMillis()
        
        return withContext(Dispatchers.IO) {
            try {
                val prompt = createParsePrompt(text)
                Log.d(TAG, "Sending request to Claude API for parsing")
                val response = makeApiCall(prompt)
                val duration = System.currentTimeMillis() - startTime

                if (response.isSuccess) {
                    Log.d(TAG, "Claude API call successful. Duration: ${duration}ms")
                    val parsed = parseResponse(response.getOrThrow())
                    Log.d(TAG, "Successfully parsed response from Claude - Title: ${parsed.title}, " +
                            "Description: ${parsed.description}, Time: ${parsed.scheduledTime}")
                    Result.success(parsed)
                } else {
                    Log.w(TAG, "Claude API call failed. Duration: ${duration}ms")
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
        Log.d(TAG, "Starting to suggest reminder time with Claude. Title: $title")
        val startTime = System.currentTimeMillis()
        
        return withContext(Dispatchers.IO) {
            try {
                val prompt = createTimeSuggestionPrompt(title, description ?: "")
                Log.d(TAG, "Sending request to Claude API for time suggestion")
                val response = makeApiCall(prompt)
                val duration = System.currentTimeMillis() - startTime

                if (response.isSuccess) {
                    Log.d(TAG, "Claude API call successful. Duration: ${duration}ms")
                    val suggestions = parseTimeSuggestions(response.getOrThrow())
                    Log.d(TAG, "Successfully parsed time suggestions from Claude. Count: ${suggestions.size}")
                    suggestions.forEachIndexed { index, suggestion ->
                        Log.d(TAG, "Suggestion $index - Time: ${suggestion.suggestedTime}, Reason: ${suggestion.reason}")
                    }
                    Result.success(suggestions)
                } else {
                    Log.w(TAG, "Claude API call failed. Duration: ${duration}ms")
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
        return withContext(Dispatchers.IO) {
            try {
                // 尝试一个简单的API调用来检查可用性
                val jsonBody = createRequestBody("Hello")
                val request = Request.Builder()
                    .url("$baseUrl/messages")
                    .addHeader("x-api-key", config.apiKey)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("anthropic-version", "2023-06-01")
                    .post(jsonBody)
                    .build()

                client.newCall(request).execute().use { response ->
                    response.isSuccessful || response.code == 401 // 401表示API密钥格式正确但无效
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
                    .url("$baseUrl/messages")
                    .addHeader("x-api-key", config.apiKey)
                    .addHeader("anthropic-version", "2023-06-01")
                    .addHeader("Content-Type", "application/json")
                    .post(jsonBody)
                    .build()

                Log.d(TAG, "Making HTTP request to Claude API")
                val startTime = System.currentTimeMillis()
                client.newCall(request).execute().use { response ->
                    val duration = System.currentTimeMillis() - startTime
                    if (response.isSuccessful) {
                        val responseBody = response.body?.string()
                        if (responseBody != null) {
                            Log.d(TAG, "HTTP request successful. Duration: ${duration}ms. Response size: ${responseBody.length} chars")
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
        Log.d(TAG, "Creating request body for Claude API")
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
        val prompt = """
            你是一个专业的日程提醒解析助手。请根据用户的输入和当前的参考时间，解析出JSON格式的提醒设置。
            当前参考时间：${java.time.LocalDateTime.now()} (例如: 2023-10-27 星期五 09:30)
            
            # 输出要求：
            1. 请严格按照指定的JSON格式返回结果，不要添加额外的字段或注释。
            2. 时间格式使用 24小时制 (HH:mm)。
            3. 日期格式使用 YYYY-MM-DD。
            4. 如果用户未指定具体日期（如只说"每天"），date 字段为 null。
            5. 如果用户未指定具体时间，time 字段默认为 "09:00"。
            
            # JSON 结构定义：
            {
              "title": "提醒的具体内容",
              "description": "详细描述（可选）",
              "date": "具体的日期 (YYYY-MM-DD) 或 今天 (如果是重复任务)",
              "time": "时间 (HH:mm)",
              "is_recurring": true/false,
              "recurrence_rule": "DAILY" | "WEEKLY" | "MONTHLY" | "YEARLY" | "NONE",
              "weekdays": [1-7] (仅在 weekly 时使用，1代表周一，7代表周日)，
              "monthdays": [1-31]  (仅在 MONTHLY 时使用) 
            }
            
            # 示例：
            输入："每天早上8点吃药"
            输出：{"title": "吃药", "description": "每天早上8点需要按时服药", "date": null, "time": "08:00", "is_recurring": true, "recurrence_rule": "DAILY", "weekdays": [], "monthdays": []}
            
            输入："下周三下午3点开会" (假设今天是 2023-10-27 周五)
            输出：{"title": "开会", "description": "下周三下午3点开会", "date": "2023-11-01", "time": "15:00", "is_recurring": false, "recurrence_rule": "NONE", "weekdays": [], "monthdays": []}
            
            # 用户输入：
            $text
        """.trimIndent()
        
        Log.d(TAG, "Created parse prompt for text: $text")
        Log.d(TAG, "Parse prompt content:\n$prompt")
        return prompt
    }

    private fun createTimeSuggestionPrompt(title: String, description: String): String {
        return """
            根据提醒标题和描述，建议合适的时间。返回JSON数组格式：
            [
                {
                    "suggestedTime": "时间戳（毫秒）",
                    "reason": "建议理由"
                }
            ]
            
            标题："$title"
            描述："$description"
            
            要求：
            1. 提供2-3个合理的时间建议
            2. 考虑任务的紧迫性和合适性
            3. 时间应该在未来，不要建议过去的时间
            4. 理由要简洁说明为什么这个时间合适
            5. 严格按照指定的JSON格式返回，不要包含其他内容
        """.trimIndent()
    }

    private fun parseResponse(responseBody: String): AiParseResult {
        try {
            Log.d(TAG, "Parsing Claude AI response")
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
                    .trim()

                val resultJson = JSONObject(cleanContent)

                val title = resultJson.getString("title")
                val description = resultJson.optString("description", null).takeIf { it.isNotEmpty() }
                
                // 解析时间
                val timeStr = resultJson.getString("time")
                val localTime = LocalTime.parse(timeStr)
                
                // 解析日期
                val dateStr = resultJson.optString("date", "")
                val scheduledTime = if (dateStr.isNullOrEmpty() || dateStr.lowercase() == "null") {
                    // 如果没有指定具体日期，使用今天或明天的时间
                    val now = LocalTime.now()
                    val targetDate = if (localTime.isAfter(now)) {
                        LocalDate.now()
                    } else {
                        LocalDate.now().plusDays(1)
                    }
                    val localDateTime = LocalDateTime.of(targetDate, localTime)
                    localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
                } else {
                    // 如果指定了具体日期
                    val localDate = LocalDate.parse(dateStr)
                    val localDateTime = LocalDateTime.of(localDate, localTime)
                    localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
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
                    }
                )
                Log.d(TAG, "Successfully parsed result from Claude AI response - Title: $title, " +
                        "Description: $description, Time: $scheduledTime, Repeat: $repeatType")
                return result
            }
        } catch (e: Exception) {
            Log.e(TAG, "Failed to parse Claude AI response", e)
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
            Log.d(TAG, "Parsing time suggestions from Claude response")
            val json = JSONObject(responseBody)
            val content = json.getJSONArray("content").getJSONObject(0).getString("text")
            Log.d(TAG, "Response content: $content")
            val suggestionsJson = JSONArray(content)

            val suggestions = mutableListOf<AiTimeSuggestion>()
            for (i in 0 until suggestionsJson.length()) {
                val suggestionJson = suggestionsJson.getJSONObject(i)
                val suggestedTime = suggestionJson.getLong("suggestedTime")
                val reason = suggestionJson.getString("reason")

                suggestions.add(AiTimeSuggestion(suggestedTime, reason))
            }
            Log.d(TAG, "Successfully parsed ${suggestions.size} time suggestions")
            return suggestions
        } catch (e: Exception) {
            Log.e(TAG, "Failed to parse time suggestions", e)
        }
        return emptyList()
    }
}