plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}
apply {
    plugin("kotlin-kapt")
}

android {
    namespace = "com.zhouz.glidesvga"
    compileSdk = 34

    defaultConfig {
        minSdk = 16
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_6
        targetCompatibility = JavaVersion.VERSION_1_6
    }
    kotlinOptions {
        jvmTarget = "1.6"
    }
}

dependencies {
    implementation(libs.svgaplayer.android)
}