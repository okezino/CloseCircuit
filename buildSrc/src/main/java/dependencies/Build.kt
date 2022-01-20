package dependencies

object Build {
    val build_tools = "com.android.tools.build:gradle:${Versions.gradle}"
    val kotlin_gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    val google_services = "com.google.gms:google-services:${Versions.play_services}"
    val crashlytics_gradle =
        "com.google.firebase:firebase-crashlytics-gradle:${Versions.crashlytics_gradle}"

    val nav_safe_args =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.nav_classpath_version}"
    val kotlinter = "org.jmailen.gradle:kotlinter-gradle:${Versions.kotlinter_gradle}"
    val hilt_android_gradle =
        "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt_android_gradle_plugin}"


}