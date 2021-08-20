import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.10"
    application
    kotlin("plugin.serialization") version "1.5.20"
}

group = "me.bjor"
version = "1.0-SNAPSHOT"

repositories {
//    jcenter()
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("io.ktor:ktor-server-netty:1.5.2")
    implementation("io.ktor:ktor-html-builder:1.5.2")
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.7.3")
    // mongo
    implementation("org.litote.kmongo:kmongo:4.2.8")
    implementation("org.litote.kmongo:kmongo-coroutine:4.2.8")
    // logging
    implementation("ch.qos.logback:logback-classic:1.2.5")
    // coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.1")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.2")
    implementation ("io.ktor:ktor-jackson:1.5.2")

}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("ServerKt")
}