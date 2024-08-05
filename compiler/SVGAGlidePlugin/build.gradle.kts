plugins {
    `java-gradle-plugin`
    id("org.jetbrains.kotlin.jvm")
    id("com.gradle.plugin-publish") version "1.2.0"
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
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

gradlePlugin {
    plugins {
        register("SVGAGlidePlugin") {
            id = "com.zhouz.plugin.SVGAGlidePlugin"
            implementationClass = "com.zhouz.plugin.SVGAGlidePlugin"
        }
    }
}

group = "com.zhouz.plugin"
version = "1.0.0-SNAPSHOT"

publishing {
    publications {
        create<MavenPublication>("SVGAGlidePlugin") {
            groupId = "com.zhouz.plugin"
            artifactId = "SVGAGlidePlugin"
            version = "1.0.0-SNAPSHOT"
        }
    }
    repositories {
        maven(uri("$rootDir/repo/"))
    }
}

dependencies {
    implementation(gradleKotlinDsl())
    compileOnly(libs.gradle.api)
    compileOnly(libs.asm.commons)
    compileOnly(libs.asm.tree)
}