plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}
//apply(plugin = "com.zhouz.plugin.ARouterAGP8Plugin")

android {
    namespace = "com.zhouz.glidesvga"
    compileSdk = 34

    defaultConfig {
        minSdk = 14
    }

    buildTypes {
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
    //implementation(libs.svgaplayer.android)
    implementation(libs.glide)
    implementation(libs.wire.runtime)
    implementation(project(":originlib"))
    kapt(libs.glide.compiler)

    implementation("org.ow2.asm:asm-commons:9.6")
    implementation("org.ow2.asm:asm-tree:9.6")
    implementation("org.ow2.asm:asm-util:9.6")
    implementation("org.ow2.asm:asm-analysis:9.6")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.7.20")
}