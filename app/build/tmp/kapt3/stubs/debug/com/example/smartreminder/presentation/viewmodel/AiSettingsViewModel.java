package com.example.smartreminder.presentation.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\b\u0010\u0010\u001a\u00020\rH\u0002J\b\u0010\u0011\u001a\u00020\rH\u0002J\u000e\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u0014J\b\u0010\u0015\u001a\u00020\rH\u0002J\u0010\u0010\u0016\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0014\u0010\u0017\u001a\u00020\r2\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0002J\b\u0010\u001a\u001a\u00020\rH\u0002R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u001b"}, d2 = {"Lcom/example/smartreminder/presentation/viewmodel/AiSettingsViewModel;", "Landroidx/lifecycle/ViewModel;", "manageAiConfigUseCase", "Lcom/example/smartreminder/domain/usecase/ManageAiConfigUseCase;", "(Lcom/example/smartreminder/domain/usecase/ManageAiConfigUseCase;)V", "_state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/smartreminder/presentation/viewmodel/AiSettingsState;", "state", "Lkotlinx/coroutines/flow/StateFlow;", "getState", "()Lkotlinx/coroutines/flow/StateFlow;", "deleteConfiguration", "", "id", "", "hideConfigForm", "loadAllConfigurations", "onEvent", "event", "Lcom/example/smartreminder/presentation/viewmodel/AiSettingsEvent;", "saveConfiguration", "setActiveConfiguration", "showConfigForm", "config", "Lcom/example/smartreminder/domain/model/AiConfig;", "testConnection", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class AiSettingsViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.smartreminder.domain.usecase.ManageAiConfigUseCase manageAiConfigUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.smartreminder.presentation.viewmodel.AiSettingsState> _state = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.example.smartreminder.presentation.viewmodel.AiSettingsState> state = null;
    
    @javax.inject.Inject()
    public AiSettingsViewModel(@org.jetbrains.annotations.NotNull()
    com.example.smartreminder.domain.usecase.ManageAiConfigUseCase manageAiConfigUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.example.smartreminder.presentation.viewmodel.AiSettingsState> getState() {
        return null;
    }
    
    public final void onEvent(@org.jetbrains.annotations.NotNull()
    com.example.smartreminder.presentation.viewmodel.AiSettingsEvent event) {
    }
    
    private final void loadAllConfigurations() {
    }
    
    private final void showConfigForm(com.example.smartreminder.domain.model.AiConfig config) {
    }
    
    private final void hideConfigForm() {
    }
    
    private final void testConnection() {
    }
    
    private final void saveConfiguration() {
    }
    
    private final void deleteConfiguration(long id) {
    }
    
    private final void setActiveConfiguration(long id) {
    }
}