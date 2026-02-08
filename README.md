# 智能提醒助手 (Smart Reminder)

一个智能化的Android提醒应用程序，结合AI技术自动解析您的自然语言输入，智能创建和管理提醒事项。

## 项目简介

智能提醒助手是一个现代化的Android应用，旨在简化用户的日常提醒管理。通过集成AI技术，它可以自动识别您输入的自然语言中的时间和事件信息，无需手动设置复杂的提醒规则。

### 核心特性

- ✨ **AI智能识别** - 自动从自然语言中提取时间和事件信息
- 📱 **现代化UI** - 使用Jetpack Compose构建的Material Design 3界面
- 🔁 **灵活提醒** - 支持单次、每日、每周、每月及自定义循环提醒
- 🔔 **可靠通知** - 基于WorkManager的后台任务系统，确保提醒准时送达
- 🔐 **数据安全** - 本地数据加密存储，保护用户隐私

## 技术架构

本项目采用MVVM架构模式，结合Clean Architecture原则：

- **表现层**: Jetpack Compose + ViewModel
- **领域层**: Use Cases + 业务逻辑
- **数据层**: Repository + Room数据库 + API客户端

### 技术栈

- **编程语言**: Kotlin
- **UI框架**: Jetpack Compose
- **架构**: MVVM + Clean Architecture
- **数据库**: SQLite + Room ORM
- **依赖注入**: Hilt (Dagger)
- **异步处理**: Coroutines + Flow
- **网络**: Retrofit + OkHttp
- **AI引擎**: 集成多种AI服务（OpenAI, Gemini, Claude等）
- **后台任务**: WorkManager
- **通知系统**: Android Notification API

## 功能详情

### 提醒管理
- 创建、编辑和删除提醒
- 支持单次提醒和多种循环模式（每日、每周、每月、自定义）
- 智能时间解析（如："明天下午3点开会"）

### AI集成
- 支持多种AI服务（OpenAI、Google Gemini、Anthropic Claude、DeepSeek等）
- 本地自然语言处理
- 智能时间与事件提取
- 自定义AI配置

### 用户体验
- 简洁直观的用户界面
- 无障碍设计支持
- 响应式布局适应各种屏幕尺寸
- 流畅的动画效果

## 项目结构

```
app/
├── presentation/          # UI层
│   ├── ui/               # Compose屏幕
│   ├── viewmodel/        # ViewModel
│   ├── navigation/       # 导航
│   └── theme/            # 主题和样式
├── domain/               # 业务逻辑层
│   ├── usecase/          # 用例
│   ├── model/            # 领域模型
│   └── repository/       # 抽象仓库接口
├── data/                 # 数据层
│   ├── repository/       # 具体仓库实现
│   ├── local/            # 本地数据源 (Room)
│   ├── ai/               # AI相关服务
│   └── di/               # 依赖注入
└── service/              # 系统服务
    ├── notification/     # 通知服务
    └── scheduler/        # 提醒调度服务
```

## 快速开始

### 环境要求

- Android Studio (推荐最新版本)
- 最低支持 Android API 21+
- Gradle 9.0
- Kotlin 2.0+

### 构建项目

1. 克隆项目:
   ```bash
   git clone https://github.com/fenianho/smart-reminder.git
   cd smart-reminder
   ```

2. 在Android Studio中打开项目

3. 同步Gradle依赖

4. 连接设备或启动模拟器

5. 运行应用

### 配置AI服务

应用支持多种AI提供商，您需要在设置中配置相应的API密钥：

1. 打开应用并进入设置页面
2. 选择AI提供商（OpenAI、Gemini、Claude等）
3. 输入API密钥
4. 保存配置

## 安全与隐私

- 本地数据加密：使用Android Keystore加密敏感数据
- 传输加密：所有API调用均使用HTTPS
- 权限管理：遵循最小权限原则
- 数据透明度：清晰说明数据使用方式
- 用户控制：允许用户完全控制其数据

## 贡献

我们欢迎社区贡献！如果您想为项目做贡献，请按照以下步骤操作：

1. Fork 项目
2. 创建功能分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 开启Pull Request

## 许可证

该项目采用 Apache License 2.0 许可证 - 查看 [LICENSE](LICENSE) 文件了解详情