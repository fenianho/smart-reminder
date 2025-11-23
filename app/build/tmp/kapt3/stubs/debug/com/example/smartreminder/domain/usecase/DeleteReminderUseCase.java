package com.example.smartreminder.domain.usecase;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0086B\u00a2\u0006\u0002\u0010\u000bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/example/smartreminder/domain/usecase/DeleteReminderUseCase;", "", "repository", "Lcom/example/smartreminder/domain/repository/ReminderRepository;", "context", "Landroid/content/Context;", "(Lcom/example/smartreminder/domain/repository/ReminderRepository;Landroid/content/Context;)V", "invoke", "", "reminder", "Lcom/example/smartreminder/domain/model/Reminder;", "(Lcom/example/smartreminder/domain/model/Reminder;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class DeleteReminderUseCase {
    @org.jetbrains.annotations.NotNull()
    private final com.example.smartreminder.domain.repository.ReminderRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    
    @javax.inject.Inject()
    public DeleteReminderUseCase(@org.jetbrains.annotations.NotNull()
    com.example.smartreminder.domain.repository.ReminderRepository repository, @dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object invoke(@org.jetbrains.annotations.NotNull()
    com.example.smartreminder.domain.model.Reminder reminder, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}