package com.example.smartreminder;

import dagger.hilt.InstallIn;
import dagger.hilt.codegen.OriginatingElement;
import dagger.hilt.components.SingletonComponent;
import dagger.hilt.internal.GeneratedEntryPoint;

@OriginatingElement(
    topLevelClass = SmartReminderApplication.class
)
@GeneratedEntryPoint
@InstallIn(SingletonComponent.class)
public interface SmartReminderApplication_GeneratedInjector {
  void injectSmartReminderApplication(SmartReminderApplication smartReminderApplication);
}
