plugins {
    `java-gradle-plugin`
}

buildscript {
    repositories {
        mavenCentral()
        google()
        maven("https://jitpack.io")
    }
    dependencies {
        classpath(libs.gradle)
        classpath(libs.kotlin.gradle.plugin)
        classpath(libs.commons.compress)
    }
}

repositories {
    mavenCentral()
    google()
    maven("https://jitpack.io")
    // 配置HMS Core SDK的Maven仓地址。
    maven("https://s01.oss.sonatype.org/service/local/repositories/snapshots/content/")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(gradleKotlinDsl())
    compileOnly(libs.gradle.api)
    compileOnly(libs.asm.commons)
    compileOnly(libs.asm.tree)
}