# Android提醒App架构规划文档

## 1. 用户需求分析

基于用户提供的偏好和需求：
- 支持单次提醒和循环提醒
- AI集成：自定义大模型，自动识别时间/事件
- 技术栈偏好：Kotlin + Jetpack Compose
- 数据库：SQLite with Room
- AI：本地TensorFlow Lite + 云端Firebase ML或自定义API

核心功能：
- 创建、编辑、删除提醒
- 单次和循环提醒（每日、每周、每月等）
- AI智能识别时间和事件（例如从文本中提取提醒信息）
- 通知管理
- 用户界面友好

## 2. 整体系统架构

采用MVVM架构模式，结合Clean Architecture原则，确保代码的可测试性和可维护性。

### 架构层级：
- **Presentation Layer**: Jetpack Compose UI + ViewModel
- **Domain Layer**: Use Cases + Business Logic
- **Data Layer**: Repository + Room Database + API Clients

### 系统组件：
- **UI组件**: 主要屏幕（提醒列表、创建提醒、设置）
- **业务逻辑**: 提醒管理、AI处理、通知调度
- **数据存储**: 本地SQLite数据库
- **AI集成**: 本地模型 + 云端API
- **服务**: 后台提醒服务、通知服务

## 3. 技术栈

### 核心技术：
- **语言**: Kotlin
- **UI框架**: Jetpack Compose
- **架构**: MVVM + Clean Architecture
- **数据库**: SQLite + Room ORM
- **依赖注入**: Hilt (Dagger)
- **并发**: Coroutines + Flow
- **网络**: Retrofit + OkHttp
- **AI本地**: TensorFlow Lite
- **AI云端**: Firebase ML Kit 或自定义API
- **通知**: Android Notification API
- **后台任务**: WorkManager

### 开发工具：
- Android Studio
- Gradle (Kotlin DSL)
- Git for version control

## 4. 数据模型设计

### 核心实体：

```kotlin
// 提醒实体
@Entity(tableName = "reminders")
data class Reminder(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String? = null,
    val reminderTime: Long, // Unix timestamp
    val isActive: Boolean = true,
    val repeatType: RepeatType = RepeatType.NONE,
    val repeatInterval: Int? = null, // 例如每周的间隔天数
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

// 重复类型枚举
enum class RepeatType {
    NONE,       // 单次
    DAILY,      // 每日
    WEEKLY,     // 每周
    MONTHLY,    // 每月
    CUSTOM      // 自定义间隔
}

// AI识别结果
data class AIExtractedInfo(
    val title: String? = null,
    val time: Long? = null,
    val repeatType: RepeatType? = null,
    val confidence: Float
)
```

### 数据库关系：
- Reminder表：存储所有提醒信息
- 潜在的扩展：Category表（提醒分类）、User表（用户偏好）

## 5. UI/UX初步设计

### 主要屏幕：
1. **提醒列表屏幕** (ListRemindersScreen)
   - 顶部：添加新提醒FAB
   - 列表：卡片式展示提醒（标题、时间、状态）
   - 底部导航：列表、设置

2. **创建/编辑提醒屏幕** (CreateReminderScreen)
   - 表单：标题、描述、时间选择器、重复选项
   - AI输入：文本输入框，AI自动提取时间/事件
   - 保存按钮

3. **设置屏幕** (SettingsScreen)
   - 通知设置
   - AI偏好设置
   - 主题设置

### 设计原则：
- Material Design 3
- 深色/浅色主题支持
- 无障碍设计（accessibility）
- 响应式布局（支持不同屏幕尺寸）

### 用户流程：
1. 用户打开App → 提醒列表
2. 点击FAB → 创建提醒屏幕
3. 输入文本 → AI自动填充时间/事件
4. 调整设置 → 保存
5. 系统在指定时间发送通知

## 6. AI集成架构

### 双重AI策略：
1. **本地AI** (TensorFlow Lite)
   - 轻量级NLP模型
   - 离线时间/事件识别
   - 快速响应，低延迟

2. **云端AI** (Firebase ML / 自定义API)
   - 更准确的识别
   - 复杂NLP任务
   - 需要网络连接

### AI处理流程：
```
用户输入文本 → 本地预处理 → 本地模型识别
                    ↓ (如果本地结果置信度低)
              云端API调用 → 结果合并 → 用户确认/自动应用
```

### 关键组件：
- **AIModelManager**: 管理本地和云端模型
- **TextAnalyzer**: 文本分析服务
- **AIResultProcessor**: 处理和合并AI结果

### 隐私考虑：
- 本地处理敏感数据
- 云端传输加密
- 用户控制数据使用

## 7. 模块划分和依赖关系

### 模块结构：
```
app/
├── presentation/          # UI层
│   ├── ui/               # Compose屏幕
│   ├── viewmodel/        # ViewModel
│   └── theme/            # 主题和样式
├── domain/               # 业务逻辑层
│   ├── usecase/          # 用例
│   ├── model/            # 领域模型
│   └── repository/       # 抽象仓库接口
├── data/                 # 数据层
│   ├── repository/       # 具体仓库实现
│   ├── local/            # 本地数据源 (Room)
│   ├── remote/           # 远程数据源 (API)
│   ├── ai/               # AI相关
│   └── di/               # 依赖注入
└── service/              # 系统服务
    ├── notification/     # 通知服务
    └── scheduler/        # 提醒调度服务
```

### 依赖关系：
- Presentation → Domain → Data
- 所有模块依赖于common/（共享工具类）
- service模块被其他模块调用

## 8. 安全和隐私考虑

### 数据安全：
- **本地存储加密**: 使用SQLCipher或Android Keystore加密数据库
- **传输加密**: HTTPS for all API calls
- **权限管理**: 最小权限原则，只请求必要权限（通知、日历访问等）

### 隐私保护：
- **GDPR合规**: 用户数据控制权
- **数据最小化**: 只收集必要数据
- **透明度**: 清晰说明数据使用
- **删除选项**: 用户可删除所有数据

### 安全措施：
- **输入验证**: 防止SQL注入、XSS
- **认证**: 如果需要用户账户，使用OAuth 2.0
- **日志记录**: 安全日志，不记录敏感信息
- **定期安全更新**: 依赖库更新和安全补丁

### AI安全：
- **模型验证**: 验证AI模型来源
- **结果过滤**: 过滤不当内容
- **离线优先**: 本地处理减少数据泄露风险

## 9. 实施计划

### Phase 1: 基础框架 (2周)
- 设置项目结构和依赖
- 实现基础MVVM架构
- 数据库设置和基础CRUD

### Phase 2: 核心功能 (3周)
- 提醒创建/编辑/删除
- 通知系统
- 单次和循环提醒

### Phase 3: AI集成 (2周)
- 本地TensorFlow Lite集成
- 云端AI API集成
- 智能文本识别

### Phase 4: UI/UX完善 (2周)
- 完整UI实现
- 用户体验优化
- 测试和bug修复

### Phase 5: 安全和发布 (1周)
- 安全审查
- 性能优化
- 发布准备

## 10. 风险评估和缓解

### 技术风险：
- **AI准确性**: 通过用户反馈和模型迭代改进
- **电池消耗**: 优化后台服务，使用WorkManager
- **兼容性**: 支持Android API 21+

### 业务风险：
- **竞争**: 提供独特AI功能差异化
- **用户采用**: 简化UI，强大AI功能

### 缓解策略：
- **敏捷开发**: 迭代发布，收集反馈
- **质量保证**: 全面测试，包括边缘情况
- **监控**: 崩溃报告和性能监控

## 11. 成功指标

- **功能完整性**: 所有核心功能正常工作
- **性能**: 启动时间<2秒，内存使用合理
- **用户体验**: 高用户满意度评分
- **AI准确性**: >80%的时间/事件识别准确率
- **安全性**: 通过安全审计，无重大漏洞

---

此规划文档将作为后续开发的基础。根据实施过程中的反馈和需求变化，可进行适当调整。