# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

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

#-----------------混淆配置设定------------------------------------------------------------------------
-optimizationpasses 5                                                           #指定代码压缩级别
-dontusemixedcaseclassnames                                                    #混淆时不会产生形形色色的类名
-dontskipnonpubliclibraryclasses                                              #指定不忽略非公共类库
-dontpreverify                                                                   #不预校验，如果需要预校验，是-dontoptimize
-ignorewarnings                                                                  #屏蔽警告
-verbose                                                                          #混淆时记录日志
-dontskipnonpubliclibraryclassmembers                                         #指定不去忽略非公共库的类成员
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*         #优化

# 保留Annotation不混淆
-keepattributes *Annotation*,InnerClasses

# 避免混淆泛型
-keepattributes Signature
# 抛出异常时保留代码行号
-keepattributes SourceFile,LineNumberTable

# 保留我们使用的四大组件，自定义的Application等等这些类不被混淆
# 因为这些子类都有可能被外部调用
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Appliction
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep public class * extends android.app.AppCompatActivity
-keep public class com.android.vending.licensing.ILicensingService
-keep class com.kzw.leisure.bean.** {*;}
-keep class com.kzw.leisure.realm.** {*;}
-keep class com.kzw.leisure.base.** {*;}
-keep class com.kzw.leisure.model.** {*;}
-keep class com.kzw.leisure.widgets.** {*;}
-keep class com.kzw.leisure.utils.** {*;}
-keep class com.kzw.leisure.network.** {*;}
-keep class retrofit2.**{*;}
-keep class okhttp3.**{*;}
-keep class okio.**{*;}
-keep class butterknife.** { *; }
-keep class cn.wanghaomiao.xpath.model.** { *; }
-keep class org.seimicrawler.xpath.** { *; }
-keep class android.util.** { *; }
-keep class com.jayway.jsonpath.** { *; }
-keep class com.transitionseverywhere.** { *; }
-keep class com.facebook.stetho.** { *; }
-keep class com.facebook.stetho.okhttp3.** { *; }
-keep class com.getkeepsafe.relinker.** { *; }
-keep class com.luhuiguo.chinese.** { *; }
-keep class com.makeramen.roundedimageview.** { *; }
-keep class com.readystatesoftware.systembarint.** { *; }
-keep class com.bumptech.glide.** { *; }
-keep class com.tbruyelle.** { *; }
-keep class com.tencent.** { *; }
-keep class com.example.jcenterlibrary.** { *; }
-keep class com.trello.** { *; }
-keep class org.apache.** { *; }
-keep class io.reactivex.** { *; }
-keep class net.minidev.** { *; }
-keep class org.hamcrest.** { *; }
-keep class org.objectweb.asm.** { *; }
-keep class com.chad.library.** { *; }
-keep class com.google.gson.** { *; }
-keep class com.google.** { *; }
-keep class org.reactivestreams.** { *; }
-keep class org.antlr.v4.runtime.** { *; }
-keep class io.realm.** { *; }
-keep class javax.inject.** { *; }
-keep class jp.wasabeef.glide.** { *; }
-keep class net.minidev.** { *; }
-keep class org.kxml2.** { *; }
-keep class xmlpull.v1.** { *; }
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$**{
  **[] $VALUES;
  public *;
}
-keep class com.bumptech.glide.GeneratedAppGlideModuleImpl {*;}
# 保留androidx下的所有类及其内部类
-keep class androidx.** {*;}

# 保留继承的
-keep public class * extends androidx.**

# 保留R下面的资源
-keep class **.R$* {*;}

# 保留本地native方法不被混淆
-keepclasseswithmembernames class * {
    native <methods>;
}

# 保留在Activity中的方法参数是view的方法，
# 这样以来我们在layout中写的onClick就不会被影响
-keepclassmembers class * extends android.app.Activity{
    public void *(android.view.View);
}

# 保留枚举类不被混淆
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# 保留我们自定义控件（继承自View）不被混淆
-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

# 保留Parcelable序列化类不被混淆
-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}

# 保留Serializable序列化的类不被混淆
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    !private <fields>;
    !private <methods>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# 对于带有回调函数的onXXEvent、**On*Listener的，不能被混淆
-keepclassmembers class * {
    void *(**On*Event);
    void *(**On*Listener);
}

# webView处理，项目中没有使用到webView忽略即可
-keepclassmembers class fqcn.of.javascript.interface.for.webview {
    public *;
}
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.webView, jav.lang.String);
}

# 移除Log类打印各个等级日志的代码，打正式包的时候可以做为禁log使用，这里可以作为禁止log打印的功能使用
# 记得proguard-android.txt中一定不要加-dontoptimize才起作用
# 另外的一种实现方案是通过BuildConfig.DEBUG的变量来控制
-assumenosideeffects class android.util.Log {
    public static int v(...);
    public static int i(...);
    public static int w(...);
    public static int d(...);
    public static int e(...);
}

# 保持js引擎调用的java类
-keep class **.analyzeRule.**{*;}
# 保持web类
-keep class **.web.**{*;}

-keepclassmembers class * {
    public <init> (org.json.JSONObject);
}

-keep public class com.kzw.leisure.R$*{
    public static final int *;
}

#gsyvideoplayer
-keep class com.shuyu.gsyvideoplayer.video.** { *; }
-dontwarn com.shuyu.gsyvideoplayer.video.**
-keep class com.shuyu.gsyvideoplayer.video.base.** { *; }
-dontwarn com.shuyu.gsyvideoplayer.video.base.**
-keep class com.shuyu.gsyvideoplayer.utils.** { *; }
-dontwarn com.shuyu.gsyvideoplayer.utils.**
-keep class tv.danmaku.ijk.** { *; }
-dontwarn tv.danmaku.ijk.**

##JSOUP
-keep class org.jsoup.**{*;}
-keep class com.monke.mprogressbar.**{ *;}

-keep class org.slf4j.**{*;}
-dontwarn org.slf4j.**

-keep class org.codehaus.**{*;}
-dontwarn org.codehaus.**
-keep class com.jayway.**{*;}
-dontwarn com.jayway.**
-keep class com.fasterxml.**{*;}

-keep class javax.swing..**{*;}
-dontwarn javax.swing.**
-keep class java.awt.**{*;}
-dontwarn java.awt.**
-keep class sun.misc.**{*;}
-dontwarn sun.misc.**
-keep class sun.reflect.**{*;}
-dontwarn sun.reflect.**
-keep class sun.misc.Unsafe { *; }

## Rhino
-keep class javax.script.** { *; }
-keep class com.sun.script.javascript.** { *; }
-keep class org.mozilla.javascript.** { *; }
-dontwarn org.mozilla.javascript.**
-dontwarn sun.**

###EPUB
-dontwarn nl.siegmann.epublib.**
-dontwarn org.xmlpull.**
-keep class nl.siegmann.epublib.**{*;}
-keep class javax.xml.**{*;}
-keep class org.xmlpull.**{*;}

-keep class org.simpleframework.xml.**{*;}
-dontwarn org.simpleframework.xml.**

#kotlin
-keep class kotlin.** { *; }
-keep class kotlin.Metadata { *; }
-dontwarn kotlin.**
-keepclassmembers class **$WhenMappings {
    <fields>;
}
-keepclassmembers class kotlin.Metadata {
    public <methods>;
}
-assumenosideeffects class kotlin.jvm.internal.Intrinsics {
    static void checkParameterIsNotNull(java.lang.Object, java.lang.String);
}
