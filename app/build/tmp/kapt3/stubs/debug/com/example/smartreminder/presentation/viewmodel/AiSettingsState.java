package com.example.smartreminder.presentation.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b,\b\u0087\b\u0018\u00002\u00020\u0001B\u00a7\u0001\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\u0006\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u000b\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000b\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0014\u001a\u00020\b\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016\u0012\b\b\u0002\u0010\u0017\u001a\u00020\b\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\u0002\u0010\u0019J\u000f\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\t\u0010/\u001a\u00020\u0012H\u00c6\u0003J\t\u00100\u001a\u00020\u0012H\u00c6\u0003J\t\u00101\u001a\u00020\bH\u00c6\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u0016H\u00c6\u0003J\t\u00103\u001a\u00020\bH\u00c6\u0003J\u000b\u00104\u001a\u0004\u0018\u00010\u000bH\u00c6\u0003J\t\u00105\u001a\u00020\u0006H\u00c6\u0003J\t\u00106\u001a\u00020\bH\u00c6\u0003J\t\u00107\u001a\u00020\u0006H\u00c6\u0003J\t\u00108\u001a\u00020\u000bH\u00c6\u0003J\t\u00109\u001a\u00020\rH\u00c6\u0003J\t\u0010:\u001a\u00020\u000bH\u00c6\u0003J\t\u0010;\u001a\u00020\u000bH\u00c6\u0003J\u000b\u0010<\u001a\u0004\u0018\u00010\u000bH\u00c6\u0003J\u00ab\u0001\u0010=\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000b2\b\b\u0002\u0010\u000f\u001a\u00020\u000b2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00122\b\b\u0002\u0010\u0014\u001a\u00020\b2\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\b\u0002\u0010\u0017\u001a\u00020\b2\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u000bH\u00c6\u0001J\u0013\u0010>\u001a\u00020\b2\b\u0010?\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010@\u001a\u00020\u0012H\u00d6\u0001J\t\u0010A\u001a\u00020\u000bH\u00d6\u0001R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u000e\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001dR\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001dR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\t\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001bR\u0013\u0010\u0018\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001dR\u0011\u0010\u0017\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010&R\u0011\u0010\u0014\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010&R\u0011\u0010\u0013\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010(R\u0011\u0010\u000f\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001dR\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010&R\u0011\u0010\u0011\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010(\u00a8\u0006B"}, d2 = {"Lcom/example/smartreminder/presentation/viewmodel/AiSettingsState;", "", "configs", "", "Lcom/example/smartreminder/domain/model/AiConfig;", "activeConfigId", "", "showForm", "", "editingConfigId", "configName", "", "selectedProvider", "Lcom/example/smartreminder/domain/model/AiProvider;", "apiKey", "modelName", "baseUrl", "timeoutMs", "", "maxRetries", "isTestingConnection", "connectionTestResult", "Lcom/example/smartreminder/presentation/viewmodel/ConnectionTestResult;", "isSaving", "error", "(Ljava/util/List;JZJLjava/lang/String;Lcom/example/smartreminder/domain/model/AiProvider;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IIZLcom/example/smartreminder/presentation/viewmodel/ConnectionTestResult;ZLjava/lang/String;)V", "getActiveConfigId", "()J", "getApiKey", "()Ljava/lang/String;", "getBaseUrl", "getConfigName", "getConfigs", "()Ljava/util/List;", "getConnectionTestResult", "()Lcom/example/smartreminder/presentation/viewmodel/ConnectionTestResult;", "getEditingConfigId", "getError", "()Z", "getMaxRetries", "()I", "getModelName", "getSelectedProvider", "()Lcom/example/smartreminder/domain/model/AiProvider;", "getShowForm", "getTimeoutMs", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "app_debug"})
public final class AiSettingsState {
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.example.smartreminder.domain.model.AiConfig> configs = null;
    private final long activeConfigId = 0L;
    private final boolean showForm = false;
    private final long editingConfigId = 0L;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String configName = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.smartreminder.domain.model.AiProvider selectedProvider = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String apiKey = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String modelName = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String baseUrl = null;
    private final int timeoutMs = 0;
    private final int maxRetries = 0;
    private final boolean isTestingConnection = false;
    @org.jetbrains.annotations.Nullable()
    private final com.example.smartreminder.presentation.viewmodel.ConnectionTestResult connectionTestResult = null;
    private final boolean isSaving = false;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String error = null;
    
    public AiSettingsState(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.smartreminder.domain.model.AiConfig> configs, long activeConfigId, boolean showForm, long editingConfigId, @org.jetbrains.annotations.NotNull()
    java.lang.String configName, @org.jetbrains.annotations.NotNull()
    com.example.smartreminder.domain.model.AiProvider selectedProvider, @org.jetbrains.annotations.NotNull()
    java.lang.String apiKey, @org.jetbrains.annotations.NotNull()
    java.lang.String modelName, @org.jetbrains.annotations.Nullable()
    java.lang.String baseUrl, int timeoutMs, int maxRetries, boolean isTestingConnection, @org.jetbrains.annotations.Nullable()
    com.example.smartreminder.presentation.viewmodel.ConnectionTestResult connectionTestResult, boolean isSaving, @org.jetbrains.annotations.Nullable()
    java.lang.String error) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.example.smartreminder.domain.model.AiConfig> getConfigs() {
        return null;
    }
    
    public final long getActiveConfigId() {
        return 0L;
    }
    
    public final boolean getShowForm() {
        return false;
    }
    
    public final long getEditingConfigId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getConfigName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.smartreminder.domain.model.AiProvider getSelectedProvider() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getApiKey() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getModelName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getBaseUrl() {
        return null;
    }
    
    public final int getTimeoutMs() {
        return 0;
    }
    
    public final int getMaxRetries() {
        return 0;
    }
    
    public final boolean isTestingConnection() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.example.smartreminder.presentation.viewmodel.ConnectionTestResult getConnectionTestResult() {
        return null;
    }
    
    public final boolean isSaving() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getError() {
        return null;
    }
    
    public AiSettingsState() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.example.smartreminder.domain.model.AiConfig> component1() {
        return null;
    }
    
    public final int component10() {
        return 0;
    }
    
    public final int component11() {
        return 0;
    }
    
    public final boolean component12() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.example.smartreminder.presentation.viewmodel.ConnectionTestResult component13() {
        return null;
    }
    
    public final boolean component14() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component15() {
        return null;
    }
    
    public final long component2() {
        return 0L;
    }
    
    public final boolean component3() {
        return false;
    }
    
    public final long component4() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.smartreminder.domain.model.AiProvider component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.smartreminder.presentation.viewmodel.AiSettingsState copy(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.smartreminder.domain.model.AiConfig> configs, long activeConfigId, boolean showForm, long editingConfigId, @org.jetbrains.annotations.NotNull()
    java.lang.String configName, @org.jetbrains.annotations.NotNull()
    com.example.smartreminder.domain.model.AiProvider selectedProvider, @org.jetbrains.annotations.NotNull()
    java.lang.String apiKey, @org.jetbrains.annotations.NotNull()
    java.lang.String modelName, @org.jetbrains.annotations.Nullable()
    java.lang.String baseUrl, int timeoutMs, int maxRetries, boolean isTestingConnection, @org.jetbrains.annotations.Nullable()
    com.example.smartreminder.presentation.viewmodel.ConnectionTestResult connectionTestResult, boolean isSaving, @org.jetbrains.annotations.Nullable()
    java.lang.String error) {
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