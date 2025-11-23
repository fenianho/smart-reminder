package com.example.smartreminder.data.local.converter;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006H\u0007J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\nH\u0007J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rH\u0007J\u001a\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00062\b\u0010\u000f\u001a\u0004\u0018\u00010\u0004H\u0007J\u0010\u0010\u0010\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0004H\u0007J\u0010\u0010\u0011\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0004H\u0007\u00a8\u0006\u0012"}, d2 = {"Lcom/example/smartreminder/data/local/converter/RepeatTypeConverter;", "", "()V", "fromMonthlyRepeatDays", "", "days", "", "", "fromMonthlyRepeatType", "monthlyRepeatType", "Lcom/example/smartreminder/domain/model/MonthlyRepeatType;", "fromRepeatType", "repeatType", "Lcom/example/smartreminder/domain/model/RepeatType;", "toMonthlyRepeatDays", "value", "toMonthlyRepeatType", "toRepeatType", "app_debug"})
public final class RepeatTypeConverter {
    
    public RepeatTypeConverter() {
        super();
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String fromRepeatType(@org.jetbrains.annotations.NotNull()
    com.example.smartreminder.domain.model.RepeatType repeatType) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.NotNull()
    public final com.example.smartreminder.domain.model.RepeatType toRepeatType(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String fromMonthlyRepeatType(@org.jetbrains.annotations.NotNull()
    com.example.smartreminder.domain.model.MonthlyRepeatType monthlyRepeatType) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.NotNull()
    public final com.example.smartreminder.domain.model.MonthlyRepeatType toMonthlyRepeatType(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String fromMonthlyRepeatDays(@org.jetbrains.annotations.Nullable()
    java.util.Set<java.lang.Integer> days) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.Nullable()
    public final java.util.Set<java.lang.Integer> toMonthlyRepeatDays(@org.jetbrains.annotations.Nullable()
    java.lang.String value) {
        return null;
    }
}