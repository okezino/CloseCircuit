package dependencies

object Dependencies {
    val kotlin_standard_library = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    val kotlin_reflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"
    val core_ktx = "androidx.core:core-ktx:${Versions.ktx}"

    val kotlin_coroutines_core =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines_version}"
    val kotlin_coroutines_android =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines_version}"
    val kotlin_coroutines_play_services =
        "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Versions.coroutines_play_services}"
    val navigation_fragment_ktx =
        "androidx.navigation:navigation-fragment-ktx:${Versions.nav_components}"
    val navigation_ui_ktx = "androidx.navigation:navigation-ui-ktx:${Versions.nav_components}"

    val lifecycle_viewmodel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle_version}"

    val lifecycle_runtime = "androidx.lifecycle:lifecycle-runtime:${Versions.lifecycle_version}"

    val hilt_android = "com.google.dagger:hilt-android:${Versions.hilt}"
    val hilt_life_cycle =
        "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hilt_lifecycle_viewmodel}"

    val hilt_navigation_compose =
        "androidx.hilt:hilt-navigation-compose:${Versions.hilt_navigation_compose}"
    val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp_version}"
    val okhttp_logging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp_version}"


    val navigation_runtime = "androidx.navigation:navigation-runtime:${Versions.nav_components}"
    val navigation_dynamic =
        "androidx.navigation:navigation-dynamic-features-fragment:${Versions.nav_components}"
    val material_dialogs = "com.afollestad.material-dialogs:core:${Versions.material_dialogs}"
    val material_dialogs_input =
        "com.afollestad.material-dialogs:input:${Versions.material_dialogs}"
    val room_runtime = "androidx.room:room-runtime:${Versions.room}"
    val room_ktx = "androidx.room:room-ktx:${Versions.room}"
    val play_core = "com.google.android.play:core:${Versions.play_core}"
    val leak_canary = "com.squareup.leakcanary:leakcanary-android:${Versions.leak_canary}"
    val firebase_analytics =
        "com.google.firebase:firebase-analytics-ktx:${Versions.firebase_analytics}"
    val firebase_crashlytics =
        "com.google.firebase:firebase-crashlytics:${Versions.firebase_crashlytics}"
    val lifecycle_coroutines =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle_version}"

    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit2_version}"
    val retrofit_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit2_version}"

    val circle_image_view = "de.hdodenhof:circleimageview:${Versions.circleimageview}"
    val otp_pin_view =
        "com.github.mukeshsolanki:android-otpview-pinview:${Versions.otpview_pinview}"

    val leung_pinview = "io.github.chaosleung:pinview:${Versions.leung_pinview}"

    val phone_picker_ccp = "com.hbb20:ccp:${Versions.phone_picker_ccp}"

    val currency_picker_android =
        "com.github.midorikocak:currency-picker-android:${Versions.currency_picker_android}"

    val viewpagerindicator =
        "com.tbuonomo.andrui:viewpagerdotsindicator:${Versions.viewpagerdotsindicator}"

}