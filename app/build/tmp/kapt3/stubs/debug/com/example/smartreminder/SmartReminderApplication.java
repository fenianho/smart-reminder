package com.example.smartreminder;

@dagger.hilt.android.HiltAndroidApp()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00132\u00020\u00012\u00020\u0002:\u0001\u0013B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u0012\u001a\u00020\u0011H\u0016R\u001e\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\r8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0014"}, d2 = {"Lcom/example/smartreminder/SmartReminderApplication;", "Landroid/app/Application;", "Landroidx/work/Configuration$Provider;", "()V", "aiServiceManager", "Lcom/example/smartreminder/data/ai/AiServiceManager;", "getAiServiceManager", "()Lcom/example/smartreminder/data/ai/AiServiceManager;", "setAiServiceManager", "(Lcom/example/smartreminder/data/ai/AiServiceManager;)V", "applicationScope", "Lkotlinx/coroutines/CoroutineScope;", "workManagerConfiguration", "Landroidx/work/Configuration;", "getWorkManagerConfiguration", "()Landroidx/work/Configuration;", "initializeAiService", "", "onCreate", "Companion", "app_debug"})
public final class SmartReminderApplication extends android.app.Application implements androidx.work.Configuration.Provider {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "SmartReminderApplication";
    @javax.inject.Inject()
    public com.example.smartreminder.data.ai.AiServiceManager aiServiceManager;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope applicationScope = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.smartreminder.SmartReminderApplication.Companion Companion = null;
    
    public SmartReminderApplication() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.smartreminder.data.ai.AiServiceManager getAiServiceManager() {
        return null;
    }
    
    public final void setAiServiceManager(@org.jetbrains.annotations.NotNull()
    com.example.smartreminder.data.ai.AiServiceManager p0) {
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    private final void initializeAiService() {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public androidx.work.Configuration getWorkManagerConfiguration() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/example/smartreminder/SmartReminderApplication$Companion;", "", "()V", "TAG", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}