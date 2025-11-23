package com.example.smartreminder.presentation.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0010\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\b\u0010\u0017\u001a\u00020\u0013H\u0002J\u000e\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001aJ\u0010\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lcom/example/smartreminder/presentation/viewmodel/ReminderListViewModel;", "Landroidx/lifecycle/ViewModel;", "getAllRemindersUseCase", "Lcom/example/smartreminder/domain/usecase/GetAllRemindersUseCase;", "createReminderUseCase", "Lcom/example/smartreminder/domain/usecase/CreateReminderUseCase;", "scheduleReminderUseCase", "Lcom/example/smartreminder/domain/usecase/ScheduleReminderUseCase;", "updateReminderUseCase", "Lcom/example/smartreminder/domain/usecase/UpdateReminderUseCase;", "(Lcom/example/smartreminder/domain/usecase/GetAllRemindersUseCase;Lcom/example/smartreminder/domain/usecase/CreateReminderUseCase;Lcom/example/smartreminder/domain/usecase/ScheduleReminderUseCase;Lcom/example/smartreminder/domain/usecase/UpdateReminderUseCase;)V", "_state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/smartreminder/presentation/viewmodel/ReminderListState;", "state", "Lkotlinx/coroutines/flow/StateFlow;", "getState", "()Lkotlinx/coroutines/flow/StateFlow;", "createReminder", "", "reminder", "Lcom/example/smartreminder/domain/model/Reminder;", "deleteReminder", "loadReminders", "onEvent", "event", "Lcom/example/smartreminder/presentation/viewmodel/ReminderListEvent;", "toggleReminder", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class ReminderListViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.smartreminder.domain.usecase.GetAllRemindersUseCase getAllRemindersUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.smartreminder.domain.usecase.CreateReminderUseCase createReminderUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.smartreminder.domain.usecase.ScheduleReminderUseCase scheduleReminderUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.smartreminder.domain.usecase.UpdateReminderUseCase updateReminderUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.smartreminder.presentation.viewmodel.ReminderListState> _state = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.example.smartreminder.presentation.viewmodel.ReminderListState> state = null;
    
    @javax.inject.Inject()
    public ReminderListViewModel(@org.jetbrains.annotations.NotNull()
    com.example.smartreminder.domain.usecase.GetAllRemindersUseCase getAllRemindersUseCase, @org.jetbrains.annotations.NotNull()
    com.example.smartreminder.domain.usecase.CreateReminderUseCase createReminderUseCase, @org.jetbrains.annotations.NotNull()
    com.example.smartreminder.domain.usecase.ScheduleReminderUseCase scheduleReminderUseCase, @org.jetbrains.annotations.NotNull()
    com.example.smartreminder.domain.usecase.UpdateReminderUseCase updateReminderUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.example.smartreminder.presentation.viewmodel.ReminderListState> getState() {
        return null;
    }
    
    public final void onEvent(@org.jetbrains.annotations.NotNull()
    com.example.smartreminder.presentation.viewmodel.ReminderListEvent event) {
    }
    
    private final void loadReminders() {
    }
    
    private final void createReminder(com.example.smartreminder.domain.model.Reminder reminder) {
    }
    
    private final void deleteReminder(com.example.smartreminder.domain.model.Reminder reminder) {
    }
    
    private final void toggleReminder(com.example.smartreminder.domain.model.Reminder reminder) {
    }
}