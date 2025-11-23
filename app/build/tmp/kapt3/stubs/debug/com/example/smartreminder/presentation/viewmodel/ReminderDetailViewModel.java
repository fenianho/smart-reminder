package com.example.smartreminder.presentation.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0012R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\u0013"}, d2 = {"Lcom/example/smartreminder/presentation/viewmodel/ReminderDetailViewModel;", "Landroidx/lifecycle/ViewModel;", "getReminderByIdUseCase", "Lcom/example/smartreminder/domain/usecase/GetReminderByIdUseCase;", "deleteReminderUseCase", "Lcom/example/smartreminder/domain/usecase/DeleteReminderUseCase;", "(Lcom/example/smartreminder/domain/usecase/GetReminderByIdUseCase;Lcom/example/smartreminder/domain/usecase/DeleteReminderUseCase;)V", "_state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/smartreminder/presentation/viewmodel/ReminderDetailState;", "state", "Lkotlinx/coroutines/flow/StateFlow;", "getState", "()Lkotlinx/coroutines/flow/StateFlow;", "deleteReminder", "", "loadReminder", "reminderId", "", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class ReminderDetailViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.smartreminder.domain.usecase.GetReminderByIdUseCase getReminderByIdUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.smartreminder.domain.usecase.DeleteReminderUseCase deleteReminderUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.smartreminder.presentation.viewmodel.ReminderDetailState> _state = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.example.smartreminder.presentation.viewmodel.ReminderDetailState> state = null;
    
    @javax.inject.Inject()
    public ReminderDetailViewModel(@org.jetbrains.annotations.NotNull()
    com.example.smartreminder.domain.usecase.GetReminderByIdUseCase getReminderByIdUseCase, @org.jetbrains.annotations.NotNull()
    com.example.smartreminder.domain.usecase.DeleteReminderUseCase deleteReminderUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.example.smartreminder.presentation.viewmodel.ReminderDetailState> getState() {
        return null;
    }
    
    public final void loadReminder(long reminderId) {
    }
    
    public final void deleteReminder() {
    }
}