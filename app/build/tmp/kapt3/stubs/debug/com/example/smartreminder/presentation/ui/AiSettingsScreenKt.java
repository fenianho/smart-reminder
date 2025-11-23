package com.example.smartreminder.presentation.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000V\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a.\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0007\u001aL\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00010\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\u000f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00010\u000f2\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0007\u001a<\u0010\u0012\u001a\u00020\u00012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0007\u001a\"\u0010\u0017\u001a\u00020\u00012\b\b\u0002\u0010\u0018\u001a\u00020\u00192\u000e\b\u0002\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00010\u000fH\u0007\u001a\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002\u00a8\u0006\u001f"}, d2 = {"AiConfigForm", "", "state", "Lcom/example/smartreminder/presentation/viewmodel/AiSettingsState;", "onEvent", "Lkotlin/Function1;", "Lcom/example/smartreminder/presentation/viewmodel/AiSettingsEvent;", "modifier", "Landroidx/compose/ui/Modifier;", "AiConfigItem", "config", "Lcom/example/smartreminder/domain/model/AiConfig;", "isActive", "", "onEdit", "Lkotlin/Function0;", "onDelete", "onSetActive", "AiConfigList", "configs", "", "activeConfigId", "", "AiSettingsScreen", "viewModel", "Lcom/example/smartreminder/presentation/viewmodel/AiSettingsViewModel;", "onNavigateBack", "getProviderDescription", "", "provider", "Lcom/example/smartreminder/domain/model/AiProvider;", "app_debug"})
public final class AiSettingsScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void AiSettingsScreen(@org.jetbrains.annotations.NotNull()
    com.example.smartreminder.presentation.viewmodel.AiSettingsViewModel viewModel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateBack) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void AiConfigList(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.smartreminder.domain.model.AiConfig> configs, long activeConfigId, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.example.smartreminder.presentation.viewmodel.AiSettingsEvent, kotlin.Unit> onEvent, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void AiConfigItem(@org.jetbrains.annotations.NotNull()
    com.example.smartreminder.domain.model.AiConfig config, boolean isActive, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onEdit, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDelete, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onSetActive, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void AiConfigForm(@org.jetbrains.annotations.NotNull()
    com.example.smartreminder.presentation.viewmodel.AiSettingsState state, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.example.smartreminder.presentation.viewmodel.AiSettingsEvent, kotlin.Unit> onEvent, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    private static final java.lang.String getProviderDescription(com.example.smartreminder.domain.model.AiProvider provider) {
        return null;
    }
}