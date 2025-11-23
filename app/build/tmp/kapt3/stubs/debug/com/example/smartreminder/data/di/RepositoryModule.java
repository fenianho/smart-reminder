package com.example.smartreminder.data.di;

/**
 * Repository模块，用于提供Repository实现
 */
@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\'J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\'\u00a8\u0006\u000b"}, d2 = {"Lcom/example/smartreminder/data/di/RepositoryModule;", "", "()V", "bindAiConfigRepository", "Lcom/example/smartreminder/domain/repository/AiConfigRepository;", "aiConfigRepositoryImpl", "Lcom/example/smartreminder/data/repository/AiConfigRepositoryImpl;", "bindReminderRepository", "Lcom/example/smartreminder/domain/repository/ReminderRepository;", "reminderRepositoryImpl", "Lcom/example/smartreminder/data/repository/ReminderRepositoryImpl;", "app_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public abstract class RepositoryModule {
    
    public RepositoryModule() {
        super();
    }
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.smartreminder.domain.repository.AiConfigRepository bindAiConfigRepository(@org.jetbrains.annotations.NotNull()
    com.example.smartreminder.data.repository.AiConfigRepositoryImpl aiConfigRepositoryImpl);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.smartreminder.domain.repository.ReminderRepository bindReminderRepository(@org.jetbrains.annotations.NotNull()
    com.example.smartreminder.data.repository.ReminderRepositoryImpl reminderRepositoryImpl);
}