package com.example.smartreminder.presentation.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0004\u0007\b\t\n\u00a8\u0006\u000b"}, d2 = {"Lcom/example/smartreminder/presentation/viewmodel/AddReminderEvent;", "", "()V", "ClearError", "CreateReminder", "ParseText", "ResetSavedState", "Lcom/example/smartreminder/presentation/viewmodel/AddReminderEvent$ClearError;", "Lcom/example/smartreminder/presentation/viewmodel/AddReminderEvent$CreateReminder;", "Lcom/example/smartreminder/presentation/viewmodel/AddReminderEvent$ParseText;", "Lcom/example/smartreminder/presentation/viewmodel/AddReminderEvent$ResetSavedState;", "app_debug"})
public abstract class AddReminderEvent {
    
    private AddReminderEvent() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/example/smartreminder/presentation/viewmodel/AddReminderEvent$ClearError;", "Lcom/example/smartreminder/presentation/viewmodel/AddReminderEvent;", "()V", "app_debug"})
    public static final class ClearError extends com.example.smartreminder.presentation.viewmodel.AddReminderEvent {
        @org.jetbrains.annotations.NotNull()
        public static final com.example.smartreminder.presentation.viewmodel.AddReminderEvent.ClearError INSTANCE = null;
        
        private ClearError() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lcom/example/smartreminder/presentation/viewmodel/AddReminderEvent$CreateReminder;", "Lcom/example/smartreminder/presentation/viewmodel/AddReminderEvent;", "reminder", "Lcom/example/smartreminder/domain/model/Reminder;", "(Lcom/example/smartreminder/domain/model/Reminder;)V", "getReminder", "()Lcom/example/smartreminder/domain/model/Reminder;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_debug"})
    public static final class CreateReminder extends com.example.smartreminder.presentation.viewmodel.AddReminderEvent {
        @org.jetbrains.annotations.NotNull()
        private final com.example.smartreminder.domain.model.Reminder reminder = null;
        
        public CreateReminder(@org.jetbrains.annotations.NotNull()
        com.example.smartreminder.domain.model.Reminder reminder) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.smartreminder.domain.model.Reminder getReminder() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.smartreminder.domain.model.Reminder component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.smartreminder.presentation.viewmodel.AddReminderEvent.CreateReminder copy(@org.jetbrains.annotations.NotNull()
        com.example.smartreminder.domain.model.Reminder reminder) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lcom/example/smartreminder/presentation/viewmodel/AddReminderEvent$ParseText;", "Lcom/example/smartreminder/presentation/viewmodel/AddReminderEvent;", "text", "", "(Ljava/lang/String;)V", "getText", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_debug"})
    public static final class ParseText extends com.example.smartreminder.presentation.viewmodel.AddReminderEvent {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String text = null;
        
        public ParseText(@org.jetbrains.annotations.NotNull()
        java.lang.String text) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getText() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.smartreminder.presentation.viewmodel.AddReminderEvent.ParseText copy(@org.jetbrains.annotations.NotNull()
        java.lang.String text) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/example/smartreminder/presentation/viewmodel/AddReminderEvent$ResetSavedState;", "Lcom/example/smartreminder/presentation/viewmodel/AddReminderEvent;", "()V", "app_debug"})
    public static final class ResetSavedState extends com.example.smartreminder.presentation.viewmodel.AddReminderEvent {
        @org.jetbrains.annotations.NotNull()
        public static final com.example.smartreminder.presentation.viewmodel.AddReminderEvent.ResetSavedState INSTANCE = null;
        
        private ResetSavedState() {
        }
    }
}