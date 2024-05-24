plugins {
    kotlin("jvm") version "1.9.0"
    application
    `java-library`
    `maven-publish`
}

group = "com.github.CzinkeM"
version = "0.1"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation ("org.seleniumhq.selenium:selenium-java:4.5.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.github.CzinkeM"
            artifactId = "thevr_happyhpour_scraper"
            version = "0.1"

            from(components["kotlin"])
        }
    }
}

