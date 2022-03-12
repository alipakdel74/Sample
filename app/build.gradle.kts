plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    defaultConfig {
        applicationId = "com.achareh.sample"

        buildConfigField("String", "BASE_URL", "\"https://stage.achareh.ir/\"")
    }
}

dependencies {

    api(project(mapOf("path" to ":core:component")))

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}