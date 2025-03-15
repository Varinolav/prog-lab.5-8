plugins {
    id("java")
    id("com.gradleup.shadow") version "9.0.0-beta10"
}

group = "ru.varino"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    // https://mvnrepository.com/artifact/com.google.code.gson/gson
    implementation("com.google.code.gson:gson:2.10.1")

}

tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    manifest.attributes["Main-Class"] = "ru.varino.Main"
}

buildscript {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
    dependencies {
        classpath("com.gradleup.shadow:shadow-gradle-plugin:7.0.0")
    }
}

// `apply plugin` stuffs are used with `buildscript`.
apply(plugin = "java")
apply(plugin = "com.gradleup.shadow")