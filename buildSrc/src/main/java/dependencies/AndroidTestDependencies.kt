package dependencies

object AndroidTestDependencies {

    val kotlin_test = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
    val coroutines_test =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines_version}"
    val espresso_core = "androidx.test.espresso:espresso-core:${Versions.espresso_core}"
    val espresso_contrib = "androidx.test.espresso:espresso-contrib:${Versions.espresso_core}"
    val wharton_expresso_idling_resource =
        "com.jakewharton.espresso:okhttp3-idling-resource:${Versions.wharton_idling_resource}"
    val idling_resource =
        "androidx.test.espresso:espresso-idling-resource:${Versions.espresso_idling_resource}"
    val hilt_android_testing = "com.google.dagger:hilt-android-testing:${Versions.hilt}"
    val test_runner = "androidx.test:runner:${Versions.test_runner}"
    val test_rules = "androidx.test:rules:${Versions.test_runner}"
    val text_core_ktx = "androidx.test:core-ktx:${Versions.test_core}"
    val mockk_android = "io.mockk:mockk-android:${Versions.mockk_version}"
    val fragment_testing = "androidx.fragment:fragment-testing:${Versions.fragment_version}"
    val androidx_test_ext = "androidx.test.ext:junit-ktx:${Versions.androidx_test_ktx_ext}"
    val navigation_testing = "androidx.navigation:navigation-testing:${Versions.nav_components}"
    val instrumentation_runner = "androidx.test.runner.AndroidJUnitRunner"
    val hiltTestRunner = "${Application.id}.common.utils.HiltTestRunner"
    val ext_junit = "androidx.test.ext:junit:${Versions.androidx_test_ext}"


}