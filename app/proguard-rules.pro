# Room Database Proguard Rules
-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Entity class *
-keep @androidx.room.Dao class *
-keepclassmembers class * {
    @androidx.room.Query <methods>;
}
-keepattributes *Annotation*,Signature,EnclosingMethod
-dontwarn androidx.room.**

# Kotlinx Serialization Proguard Rules
-keepattributes *Annotation*,InnerClasses,Signature
-keepclassmembers class * {
    @kotlinx.serialization.Serializable <fields>;
    @kotlinx.serialization.Serializable <methods>;
}
-keep @kotlinx.serialization.Serializable class * {
    *;
}
-dontwarn kotlinx.serialization.**

# Optional / Annotation Processor references
-dontwarn java.sql.**
-dontwarn javax.lang.model.**
-dontwarn com.google.auto.common.**
-dontwarn com.squareup.javapoet.**
-dontwarn org.sqlite.**