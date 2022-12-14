# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /home/muvi/Android/Sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-dontwarn com.squareup.okhttp.**
-dontwarn com.spotxchange.v3.**
-dontwarn com.moat.analytics.**
-optimizations   code/simplification/arithmetic,!code/simplification/cast,!field/*,!method/inlining/*
-optimizationpasses 5
-allowaccessmodification
-keep class com.home.vod.CastOptionsProvider { *; }
-keep class android.support.** { *; }
-keep class com.google.** { *; }

-keep public class com.adjust.sdk.** { *; }
-keep class com.google.android.gms.common.ConnectionResult {
    int SUCCESS;
}
-keep class com.google.android.gms.ads.identifier.AdvertisingIdClient {
    com.google.android.gms.ads.identifier.AdvertisingIdClient$Info getAdvertisingIdInfo(android.content.Context);
}
-keep class com.google.android.gms.ads.identifier.AdvertisingIdClient$Info {
    java.lang.String getId();
    boolean isLimitAdTrackingEnabled();
}
-keep public class com.android.installreferrer.** { *; }
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-verbose