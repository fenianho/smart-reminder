package com.example.smartreminder.presentation.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u001aJ\u0010\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u001dH\u0002R\u000e\u0010\u000b\u001a\u00020\fX\u0082D\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lcom/example/smartreminder/presentation/viewmodel/EditReminderViewModel;", "Landroidx/lifecycle/ViewModel;", "getReminderByIdUseCase", "Lcom/example/smartreminder/domain/usecase/GetReminderByIdUseCase;", "updateReminderUseCase", "Lcom/example/smartreminder/domain/usecase/UpdateReminderUseCase;", "scheduleReminderUseCase", "Lcom/example/smartreminder/domain/usecase/ScheduleReminderUseCase;", "reminderScheduler", "Lcom/example/smartreminder/service/scheduler/ReminderScheduler;", "(Lcom/example/smartreminder/domain/usecase/GetReminderByIdUseCase;Lcom/example/smartreminder/domain/usecase/UpdateReminderUseCase;Lcom/example/smartreminder/domain/usecase/ScheduleReminderUseCase;Lcom/example/smartreminder/service/scheduler/ReminderScheduler;)V", "TAG", "", "_state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/smartreminder/presentation/viewmodel/EditReminderState;", "state", "Lkotlinx/coroutines/flow/StateFlow;", "getState", "()Lkotlinx/coroutines/flow/StateFlow;", "loadReminder", "", "reminderId", "", "onEvent", "event", "Lcom/example/smartreminder/presentation/viewmodel/EditReminderEvent;", "updateReminder", "reminder", "Lcom/example/smartreminder/domain/model/Reminder;", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class EditReminderViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.smartreminder.domain.usecase.GetReminderByIdUseCase getReminderByIdUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.smartreminder.domain.usecase.UpdateReminderUseCase updateReminderUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.smartreminder.domain.usecase.ScheduleReminderUseCase scheduleReminderUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.smartreminder.service.scheduler.ReminderScheduler reminderScheduler = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String TAG = "EditReminderViewModel";
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.smartreminder.presentation.viewmodel.EditReminderState> _state = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.example.smartreminder.presentation.viewmodel.EditReminderState> state = null;
    
    @javax.inject.Inject()
    public EditReminderViewModel(@org.jetbrains.annotations.NotNull()
    com.example.smartreminder.domain.usecase.GetReminderByIdUseCase getReminderByIdUseCase, @org.jetbrains.annotations.NotNull()
    com.example.smartreminder.domain.usecase.UpdateReminderUseCase updateReminderUseCase, @org.jetbrains.annotations.NotNull()
    com.example.smartreminder.domain.usecase.ScheduleReminderUseCase scheduleReminderUseCase, @org.jetbrains.annotations.NotNull()
    com.example.smartreminder.service.scheduler.ReminderScheduler reminderScheduler) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.example.smartreminder.presentation.viewmodel.EditReminderState> getState() {
        return null;
    }
    
    public final void loadReminder(long reminderId) {
    }
    
    public final void onEvent(@org.jetbrains.annotations.NotNull()
    com.example.smartreminder.presentation.viewmodel.EditReminderEvent event) {
    }
    
    private final void updateReminder(com.example.smartreminder.domain.model.Reminder reminder) {
    }
}