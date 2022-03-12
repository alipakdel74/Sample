import com.android.build.gradle.BaseExtension

// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.4")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

subprojects{
    afterEvaluate {
        plugins.withType<BasePlugin>{
            configure<BaseExtension>{
                compileSdkVersion(31)
                defaultConfig {
                    minSdk = 17
                    targetSdk  =31
                    versionCode =1
                    versionName = "1.0"

                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                    vectorDrawables.useSupportLibrary = true
                    multiDexEnabled = true
                }

                viewBinding.isEnabled = true

                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_1_8
                    targetCompatibility = JavaVersion.VERSION_1_8
                }


                buildTypes {
                    getByName("release") {
                        isMinifyEnabled = false
                        debuggable(false)
                        proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
                    }
                    getByName("debug") {
                        isMinifyEnabled = false
                        debuggable(true)
                        proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
                    }
                }

                (this as ExtensionAware).configure<org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions> {
                    jvmTarget = "1.8"
                }

            }
        }
    }
}

tasks.register("clean") {
    delete(rootProject.buildDir)
}