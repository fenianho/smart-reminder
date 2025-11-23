package com.example.smartreminder.data.local;

/**
 * 安全存储管理器
 * 用于安全存储API密钥和其他敏感数据
 */
@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\b\u0007\u0018\u0000 &2\u00020\u0001:\u0001&B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fJ\u000e\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u000fJ\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0014\u001a\u00020\u0015J\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0017\u001a\u00020\u0018J\u0010\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0010\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\b\u0010\u001b\u001a\u00020\u001cH\u0002J\u000e\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0017\u001a\u00020\u0018J\u0016\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010 \u001a\u00020\u000fJ\u000e\u0010!\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\"\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\u0018J\u0016\u0010#\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010$\u001a\u00020\u000fJ\u0016\u0010%\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010 \u001a\u00020\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\'"}, d2 = {"Lcom/example/smartreminder/data/local/SecureStorageManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "encryptedPrefs", "Landroid/content/SharedPreferences;", "keyStore", "Ljava/security/KeyStore;", "kotlin.jvm.PlatformType", "masterKey", "Landroidx/security/crypto/MasterKey;", "clearAll", "", "decryptData", "", "encryptedData", "encryptData", "data", "getAiConfig", "configId", "", "getApiKey", "provider", "Lcom/example/smartreminder/domain/model/AiProvider;", "getApiKeyPrefKey", "getConfigPrefKey", "getOrCreateSecretKey", "Ljavax/crypto/SecretKey;", "hasApiKey", "", "isValidApiKeyFormat", "apiKey", "removeAiConfig", "removeApiKey", "storeAiConfig", "configJson", "storeApiKey", "Companion", "app_debug"})
public final class SecureStorageManager {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    private final java.security.KeyStore keyStore = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.security.crypto.MasterKey masterKey = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.SharedPreferences encryptedPrefs = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String PREFS_FILE_NAME = "secure_prefs";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String API_KEY_PREFIX = "api_key_";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String CONFIG_PREFIX = "ai_config_";
    @org.jetbrains.annotations.NotNull()
    public static final com.example.smartreminder.data.local.SecureStorageManager.Companion Companion = null;
    
    @javax.inject.Inject()
    public SecureStorageManager(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    /**
     * 存储API密钥
     */
    public final void storeApiKey(@org.jetbrains.annotations.NotNull()
    com.example.smartreminder.domain.model.AiProvider provider, @org.jetbrains.annotations.NotNull()
    java.lang.String apiKey) {
    }
    
    /**
     * 获取API密钥
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getApiKey(@org.jetbrains.annotations.NotNull()
    com.example.smartreminder.domain.model.AiProvider provider) {
        return null;
    }
    
    /**
     * 删除API密钥
     */
    public final void removeApiKey(@org.jetbrains.annotations.NotNull()
    com.example.smartreminder.domain.model.AiProvider provider) {
    }
    
    /**
     * 检查API密钥是否存在
     */
    public final boolean hasApiKey(@org.jetbrains.annotations.NotNull()
    com.example.smartreminder.domain.model.AiProvider provider) {
        return false;
    }
    
    /**
     * 存储AI配置
     */
    public final void storeAiConfig(long configId, @org.jetbrains.annotations.NotNull()
    java.lang.String configJson) {
    }
    
    /**
     * 获取AI配置
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getAiConfig(long configId) {
        return null;
    }
    
    /**
     * 删除AI配置
     */
    public final void removeAiConfig(long configId) {
    }
    
    /**
     * 清除所有存储的数据
     */
    public final void clearAll() {
    }
    
    /**
     * 验证API密钥格式（不验证真实性）
     */
    public final boolean isValidApiKeyFormat(@org.jetbrains.annotations.NotNull()
    com.example.smartreminder.domain.model.AiProvider provider, @org.jetbrains.annotations.NotNull()
    java.lang.String apiKey) {
        return false;
    }
    
    private final java.lang.String getApiKeyPrefKey(com.example.smartreminder.domain.model.AiProvider provider) {
        return null;
    }
    
    private final java.lang.String getConfigPrefKey(long configId) {
        return null;
    }
    
    /**
     * 加密方法（如果需要额外的加密层）
     * 注意：EncryptedSharedPreferences已经提供了加密，这里是可选的额外层
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String encryptData(@org.jetbrains.annotations.NotNull()
    java.lang.String data) {
        return null;
    }
    
    /**
     * 解密方法
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String decryptData(@org.jetbrains.annotations.NotNull()
    java.lang.String encryptedData) {
        return null;
    }
    
    private final javax.crypto.SecretKey getOrCreateSecretKey() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lcom/example/smartreminder/data/local/SecureStorageManager$Companion;", "", "()V", "API_KEY_PREFIX", "", "CONFIG_PREFIX", "PREFS_FILE_NAME", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}