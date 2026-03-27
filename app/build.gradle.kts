import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinMultiplatformPlugin

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("plugin.serialization")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "com.example.myapp"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.6.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")
    implementation("androidx.compose.ui:ui:1.2.0") // Jetpack Compose UI
    implementation("androidx.compose.material:material:1.2.0") // Material Design components
    implementation("androidx.compose.ui:ui-tooling-preview:1.2.0") // Preview support
    implementation("androidx.activity:activity-compose:1.4.0") // Activity support for Compose
}

kotlin {
    jvmToolchain(11)
}