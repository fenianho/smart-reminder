# Smart Reminder

An intelligent Android reminder application that combines AI technology to automatically parse your natural language input and intelligently create and manage reminder items.

## Project Overview

Smart Reminder is a modern Android application designed to simplify daily reminder management for users. By integrating AI technology, it can automatically recognize time and event information from your natural language inputs without requiring manual setup of complex reminder rules.

### Core Features

- ✨ **AI-Powered Recognition** - Automatically extracts time and event information from natural language
- 📱 **Modern UI** - Material Design 3 interface built with Jetpack Compose
- 🔁 **Flexible Reminders** - Supports single, daily, weekly, monthly, and custom recurring reminders
- 🔔 **Reliable Notifications** - Background task system based on WorkManager to ensure timely reminders
- 🔐 **Data Security** - Encrypted local data storage protecting user privacy

## Technical Architecture

This project adopts the MVVM architecture pattern combined with Clean Architecture principles:

- **Presentation Layer**: Jetpack Compose + ViewModel
- **Domain Layer**: Use Cases + Business Logic
- **Data Layer**: Repository + Room Database + API clients

### Tech Stack

- **Programming Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM + Clean Architecture
- **Database**: SQLite + Room ORM
- **Dependency Injection**: Hilt (Dagger)
- **Asynchronous Processing**: Coroutines + Flow
- **Network**: Retrofit + OkHttp
- **AI Engine**: Integration of multiple AI services (OpenAI, Gemini, Claude, etc.)
- **Background Tasks**: WorkManager
- **Notification System**: Android Notification API

## Feature Details

### Reminder Management
- Create, edit, and delete reminders
- Support for single reminders and various recurrence patterns (daily, weekly, monthly, custom)
- Intelligent time parsing (e.g., "meeting tomorrow at 3 PM")

### AI Integration
- Support for multiple AI providers (OpenAI, Google Gemini, Anthropic Claude, DeepSeek, etc.)
- Local natural language processing
- Smart time and event extraction
- Custom AI configuration

### User Experience
- Clean and intuitive user interface
- Accessibility design support
- Responsive layout adapting to various screen sizes
- Smooth animation effects

## Project Structure

```
app/
├── presentation/          # UI Layer
│   ├── ui/               # Compose screens
│   ├── viewmodel/        # ViewModels
│   ├── navigation/       # Navigation
│   └── theme/            # Themes and styles
├── domain/               # Business Logic Layer
│   ├── usecase/          # Use cases
│   ├── model/            # Domain models
│   └── repository/       # Abstract repository interfaces
├── data/                 # Data Layer
│   ├── repository/       # Concrete repository implementations
│   ├── local/            # Local data sources (Room)
│   ├── ai/               # AI related services
│   └── di/               # Dependency injection
└── service/              # System Services
    ├── notification/     # Notification services
    └── scheduler/        # Reminder scheduling services
```

## Getting Started

### Requirements

- Android Studio (latest recommended)
- Minimum Android API level 21+
- Gradle 9.0
- Kotlin 2.0+

### Building the Project

1. Clone the project:
   ```bash
   git clone https://github.com/fenianho/smart-reminder.git
   cd smart-reminder
   ```

2. Open the project in Android Studio

3. Sync Gradle dependencies

4. Connect device or launch emulator

5. Run the application

### Configuring AI Services

The application supports multiple AI providers. You need to configure the corresponding API keys in the settings:

1. Open the app and go to the settings page
2. Select AI provider (OpenAI, Gemini, Claude, etc.)
3. Enter your API key
4. Save the configuration

## Security & Privacy

- Local data encryption: Uses Android Keystore to encrypt sensitive data
- Transmission encryption: All API calls use HTTPS
- Permission management: Follows minimum permission principle
- Data transparency: Clearly explains data usage practices
- User control: Allows users full control over their data

## Contributing

We welcome community contributions! If you'd like to contribute to the project, please follow these steps:

1. Fork the project
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details

Thank you for using Smart Reminder! We are committed to providing you with the best reminder management experience.