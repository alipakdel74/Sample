plugins {
    id ("com.android.library")
    kotlin ("android")
}

dependencies {

    api ("androidx.appcompat:appcompat:1.4.1")
    api("androidx.constraintlayout:constraintlayout:2.1.3")
    api("com.google.android.material:material:1.5.0")

    api("androidx.navigation:navigation-fragment-ktx:2.5.0-alpha03")
    api("androidx.navigation:navigation-ui-ktx:2.5.0-alpha03")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0")


    api(project(mapOf("path" to ":core:data")))
}