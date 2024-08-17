plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.svga.glide"
    compileSdk = 34

    defaultConfig {
        minSdk = 14
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
        }
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.svgaplayer.android)
    implementation(libs.glide)
    implementation(libs.wire.runtime)
    kapt(libs.glide.compiler)
}

project.description = "Use Glide to load the SVGA lib"
project.group = "io.github.zzechao"
project.version = "1.0.1"
project.ext {
    set("artifactId", "libglidesvga")
    set("url", "https://github.com/zzechao/svgaplayer-android-glide_feature")
    set("feature", "https://github.com/zzechao/svgaplayer-android-glide_feature/tree/master")
    set(
        "scm",
        mapOf(
            Pair("connectionUrl", "scm:git@github.com:zzechao/svgaplayer-android-glide_feature.git"),
            Pair("developerConnectionUrl", "scm:git@github.com:zzechao/svgaplayer-android-glide_feature.git")
        )
    )
}
apply(from = "${project.projectDir}/publish-maven.gradle")