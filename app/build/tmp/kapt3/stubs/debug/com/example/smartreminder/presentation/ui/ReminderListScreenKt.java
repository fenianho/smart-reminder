package com.example.smartreminder.presentation.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000<\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0003\u001a:\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006H\u0003\u001aR\u0010\t\u001a\u00020\u00012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\r2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\r2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\rH\u0003\u001aH\u0010\u0010\u001a\u00020\u00012\b\b\u0002\u0010\u0011\u001a\u00020\u00122\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00010\u00062\u0014\b\u0002\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\r2\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006H\u0007\u001a\u001a\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u0018H\u0002\u00a8\u0006\u001a"}, d2 = {"CountdownTimerText", "", "reminder", "Lcom/example/smartreminder/domain/model/Reminder;", "ReminderCard", "onClick", "Lkotlin/Function0;", "onDelete", "onToggle", "ReminderList", "reminders", "", "onReminderClick", "Lkotlin/Function1;", "onDeleteReminder", "onToggleReminder", "ReminderListScreen", "viewModel", "Lcom/example/smartreminder/presentation/viewmodel/ReminderListViewModel;", "onAddReminder", "onSettingsClick", "formatReminderTime", "", "reminderTime", "", "currentTime", "app_debug"})
public final class ReminderListScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void ReminderListScreen(@org.jetbrains.annotations.NotNull()
    com.example.smartreminder.presentation.viewmodel.ReminderListViewModel viewModel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onAddReminder, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.example.smartreminder.domain.model.Reminder, kotlin.Unit> onReminderClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onSettingsClick) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ReminderList(java.util.List<com.example.smartreminder.domain.model.Reminder> reminders, kotlin.jvm.functions.Function1<? super com.example.smartreminder.domain.model.Reminder, kotlin.Unit> onReminderClick, kotlin.jvm.functions.Function1<? super com.example.smartreminder.domain.model.Reminder, kotlin.Unit> onDeleteReminder, kotlin.jvm.functions.Function1<? super com.example.smartreminder.domain.model.Reminder, kotlin.Unit> onToggleReminder) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ReminderCard(com.example.smartreminder.domain.model.Reminder reminder, kotlin.jvm.functions.Function0<kotlin.Unit> onClick, kotlin.jvm.functions.Function0<kotlin.Unit> onDelete, kotlin.jvm.functions.Function0<kotlin.Unit> onToggle) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void CountdownTimerText(com.example.smartreminder.domain.model.Reminder reminder) {
    }
    
    private static final java.lang.String formatReminderTime(long reminderTime, long currentTime) {
        return null;
    }
}