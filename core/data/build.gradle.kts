plugins {
    id ("com.android.library")
    id("kotlin-kapt")
    kotlin ("android")
}

dependencies {

    api ("androidx.core:core-ktx:1.7.0")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.3")

    implementation("com.squareup.moshi:moshi:1.12.0")
    kapt("com.squareup.moshi:moshi-kotlin-codegen:1.12.0")

    api("io.insert-koin:koin-android:3.1.5")
    api("io.insert-koin:koin-android-compat:3.1.5")
    api("io.insert-koin:koin-androidx-workmanager:3.1.5")
}