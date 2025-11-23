package com.example.smartreminder.data.ai

import java.time.LocalDateTime

/**
 * AI提示词模板
 */
object PromptTemplate {
    
    /**
     * 创建解析提醒文本的提示词
     */
    public fun createParsePrompt(text: String): String {
        return """
            你是一个专业的日程提醒解析助手。请根据用户的输入和当前的参考时间，解析出JSON格式的提醒设置。
            当前参考时间：${LocalDateTime.now()} (例如: 2023-10-27 星期五 09:30)
            
            # 输出要求：
            1. 请严格按照指定的JSON格式返回结果，不要添加额外的字段或注释。
            2. 时间格式使用 24小时制 (HH:mm)。
            3. 日期格式使用 YYYY-MM-DD。
            4. 如果用户未指定具体日期（如只说"每天"），date 字段为 null。
            5. 如果用户未指定具体时间，time 字段默认为 "09:00"。
            6. 特别注意中文的表达方式，例如：
               - "每月第二个周末" 应该被理解为每月的第二个周六或周日
               - "周末" 通常指周六和周日
               - "工作日" 指周一到周五
               - "月末" 指每月的最后一天
            
            # JSON 结构定义：
            {
              "title": "提醒的具体内容",
              "description": "详细描述（可选）",
              "date": "具体的日期 (YYYY-MM-DD) 或 今天 (如果是重复任务)",
              "time": "时间 (HH:mm)",
              "is_recurring": true/false,
              "recurrence_rule": "DAILY" | "WEEKLY" | "MONTHLY" | "YEARLY" | "NONE",
              "weekdays": [1-7] (仅在 weekly 时使用，1代表周一，7代表周日)，
              "monthdays": [1-31]  (仅在 MONTHLY 按日期重复时使用，对于"月末"使用31表示)，
              "monthly_week": [-1,1-4] (仅在 MONTHLY 按星期重复时使用，-1代表最后一个，1-4代表第一到第四个)，
              "monthly_weekday": [1-7] (仅在 MONTHLY 按星期重复时使用，1代表周一，7代表周日)
            }
            
            # 特殊规则说明：
            1. 对于按月重复，有两种方式：
               - 按日期重复：如"每月5号"，使用 monthdays 字段
               - 按星期重复：如"每月第二个周末"，使用 monthly_week 和 monthly_weekday 字段
            2. 对于"每月第二个周末"这样的表达：
               - 应该设置 monthly_week=2 和 monthly_weekday=[6,7]
            3. 对于"每月最后一个工作日"这样的表达：
               - 应该设置 monthly_week=-1 和 monthly_weekday=[1,2,3,4,5]
            4. 对于"每月5号"这样的表达：
               - 应该设置 monthdays=[5]
            5. 对于"月末"这样的表达：
               - 应该设置 monthdays=[31]
               
            # 示例：
            输入："每天早上8点吃药"
            输出：{"title": "吃药", "description": "每天早上8点需要按时服药", "date": null, "time": "08:00", "is_recurring": true, "recurrence_rule": "DAILY", "weekdays": [], "monthdays": [], "monthly_week": null, "monthly_weekday": []}
            
            输入："每周一、三、五上午9点晨会"
            输出：{"title": "晨会", "description": "每周一、三、五上午9点晨会", "date": null, "time": "09:00", "is_recurring": true, "recurrence_rule": "WEEKLY", "weekdays": [1,3,5], "monthdays": [], "monthly_week": null, "monthly_weekday": []}
            
            输入："每月第二个周末下午3点团建"
            输出：{"title": "团建", "description": "每月第二个周末下午3点团建", "date": null, "time": "15:00", "is_recurring": true, "recurrence_rule": "MONTHLY", "weekdays": [], "monthdays": [], "monthly_week": 2, "monthly_weekday": [6,7]}
            
            输入："每月5号上午10点发工资"
            输出：{"title": "发工资", "description": "每月5号上午10点发工资", "date": null, "time": "10:00", "is_recurring": true, "recurrence_rule": "MONTHLY", "weekdays": [], "monthdays": [5], "monthly_week": null, "monthly_weekday": []}
            
            输入："每月最后一天晚上10点检查系统"
            输出：{"title": "检查系统", "description": "每月最后一天晚上10点检查系统", "date": null, "time": "22:00", "is_recurring": true, "recurrence_rule": "MONTHLY", "weekdays": [], "monthdays": [31], "monthly_week": null, "monthly_weekday": []}
            
            输入："每月最后一个周五下午4点总结会议"
            输出：{"title": "总结会议", "description": "每月最后一个周五下午4点总结会议", "date": null, "time": "16:00", "is_recurring": true, "recurrence_rule": "MONTHLY", "weekdays": [], "monthdays": [], "monthly_week": -1, "monthly_weekday": [5]}
            
            输入："下周三下午3点开会" (假设今天是 2023-10-27 周五)
            输出：{"title": "开会", "description": "下周三下午3点开会", "date": "2023-11-01", "time": "15:00", "is_recurring": false, "recurrence_rule": "NONE", "weekdays": [], "monthdays": [], "monthly_week": null, "monthly_weekday": []}
            
            # 用户输入：
            $text
        """.trimIndent()
    }
    
    /**
     * 创建时间建议的提示词
     */
    public fun createTimeSuggestionPrompt(title: String, description: String): String {
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
        """.trimIndent()
    }
}