package com.example.smartreminder.presentation.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u00008\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\u001a\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0003\u001aP\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00010\f2\u0014\b\u0002\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u000e2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00010\fH\u0007\u001a\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\bH\u0002\u001a\u0010\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\bH\u0002\u00a8\u0006\u0014"}, d2 = {"ReminderDetailContent", "", "reminder", "Lcom/example/smartreminder/domain/model/Reminder;", "modifier", "Landroidx/compose/ui/Modifier;", "ReminderDetailScreen", "reminderId", "", "viewModel", "Lcom/example/smartreminder/presentation/viewmodel/ReminderDetailViewModel;", "onNavigateBack", "Lkotlin/Function0;", "onEditReminder", "Lkotlin/Function1;", "onDeleteReminder", "formatReminderTime", "", "timeMillis", "formatTimestamp", "app_debug"})
public final class ReminderDetailScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void ReminderDetailScreen(long reminderId, @org.jetbrains.annotations.NotNull()
    com.example.smartreminder.presentation.viewmodel.ReminderDetailViewModel viewModel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateBack, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.example.smartreminder.domain.model.Reminder, kotlin.Unit> onEditReminder, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDeleteReminder) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ReminderDetailContent(com.example.smartreminder.domain.model.Reminder reminder, androidx.compose.ui.Modifier modifier) {
    }
    
    private static final java.lang.String formatReminderTime(long timeMillis) {
        return null;
    }
    
    private static final java.lang.String formatTimestamp(long timeMillis) {
        return null;
    }
}