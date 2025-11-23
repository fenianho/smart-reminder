package com.example.smartreminder.data.local

import android.content.Context
import android.content.SharedPreferences
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.smartreminder.domain.model.AiProvider
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 安全存储管理器
 * 用于安全存储API密钥和其他敏感数据
 */
@Singleton
class SecureStorageManager @Inject constructor(
    private val context: Context
) {

    private val keyStore = KeyStore.getInstance("AndroidKeyStore").apply {
        load(null)
    }

    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val encryptedPrefs: SharedPreferences = EncryptedSharedPreferences.create(
        context,
        PREFS_FILE_NAME,
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    companion object {
        private const val PREFS_FILE_NAME = "secure_prefs"
        private const val API_KEY_PREFIX = "api_key_"
        private const val CONFIG_PREFIX = "ai_config_"
    }

    /**
     * 存储API密钥
     */
    fun storeApiKey(provider: AiProvider, apiKey: String) {
        val key = getApiKeyPrefKey(provider)
        encryptedPrefs.edit()
            .putString(key, apiKey)
            .apply()
    }

    /**
     * 获取API密钥
     */
    fun getApiKey(provider: AiProvider): String? {
        val key = getApiKeyPrefKey(provider)
        return encryptedPrefs.getString(key, null)
    }

    /**
     * 删除API密钥
     */
    fun removeApiKey(provider: AiProvider) {
        val key = getApiKeyPrefKey(provider)
        encryptedPrefs.edit()
            .remove(key)
            .apply()
    }

    /**
     * 检查API密钥是否存在
     */
    fun hasApiKey(provider: AiProvider): Boolean {
        return getApiKey(provider) != null
    }

    /**
     * 存储AI配置
     */
    fun storeAiConfig(configId: Long, configJson: String) {
        val key = getConfigPrefKey(configId)
        encryptedPrefs.edit()
            .putString(key, configJson)
            .apply()
    }

    /**
     * 获取AI配置
     */
    fun getAiConfig(configId: Long): String? {
        val key = getConfigPrefKey(configId)
        return encryptedPrefs.getString(key, null)
    }

    /**
     * 删除AI配置
     */
    fun removeAiConfig(configId: Long) {
        val key = getConfigPrefKey(configId)
        encryptedPrefs.edit()
            .remove(key)
            .apply()
    }

    /**
     * 清除所有存储的数据
     */
    fun clearAll() {
        encryptedPrefs.edit().clear().apply()
    }

    /**
     * 验证API密钥格式（不验证真实性）
     */
    fun isValidApiKeyFormat(provider: AiProvider, apiKey: String): Boolean {
        if (apiKey.isBlank()) return false

        return when (provider) {
            AiProvider.OPENAI -> apiKey.startsWith("sk-") && apiKey.length > 20
            AiProvider.DEEPSEEK -> apiKey.startsWith("sk-") || apiKey.length >= 20
            AiProvider.CLAUDE -> apiKey.startsWith("sk-ant-") && apiKey.length > 30
            AiProvider.GEMINI -> apiKey.length >= 30 // Gemini API keys are typically long
            AiProvider.CUSTOM -> apiKey.length >= 10 // Custom providers have minimal validation
        }
    }

    private fun getApiKeyPrefKey(provider: AiProvider): String {
        return "$API_KEY_PREFIX${provider.name.lowercase()}"
    }

    private fun getConfigPrefKey(configId: Long): String {
        return "$CONFIG_PREFIX$configId"
    }

    /**
     * 加密方法（如果需要额外的加密层）
     * 注意：EncryptedSharedPreferences已经提供了加密，这里是可选的额外层
     */
    fun encryptData(data: String): String {
        val key = getOrCreateSecretKey()
        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        cipher.init(Cipher.ENCRYPT_MODE, key)
        val encrypted = cipher.doFinal(data.toByteArray(Charsets.UTF_8))
        val iv = cipher.iv
        val combined = ByteArray(iv.size + encrypted.size)
        System.arraycopy(iv, 0, combined, 0, iv.size)
        System.arraycopy(encrypted, 0, combined, iv.size, encrypted.size)
        return Base64.encodeToString(combined, Base64.DEFAULT)
    }

    /**
     * 解密方法
     */
    fun decryptData(encryptedData: String): String {
        val key = getOrCreateSecretKey()
        val combined = Base64.decode(encryptedData, Base64.DEFAULT)
        val iv = combined.copyOfRange(0, 12) // GCM IV is 12 bytes
        val encrypted = combined.copyOfRange(12, combined.size)

        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        cipher.init(Cipher.DECRYPT_MODE, key, IvParameterSpec(iv))
        val decrypted = cipher.doFinal(encrypted)
        return String(decrypted, Charsets.UTF_8)
    }

    private fun getOrCreateSecretKey(): SecretKey {
        val keyAlias = "smart_reminder_key"
        return if (keyStore.containsAlias(keyAlias)) {
            keyStore.getKey(keyAlias, null) as SecretKey
        } else {
            val keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore")
            val keyGenParameterSpec = KeyGenParameterSpec.Builder(
                keyAlias,
                KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
            )
                .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                .setKeySize(256)
                .build()

            keyGenerator.init(keyGenParameterSpec)
            keyGenerator.generateKey()
        }
    }
}