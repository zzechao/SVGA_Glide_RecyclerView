plugins {
    `java-gradle-plugin`
    id("org.jetbrains.kotlin.jvm")
    //id("com.gradle.plugin-publish") version "0.21.0"
    id("com.gradle.plugin-publish") version "1.2.1"
}

repositories {
    google()
    mavenCentral()
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

apply(from = "./publish.gradle")