// SPDX-FileCopyrightText: 2021-2022 Alliander N.V.
//
// SPDX-License-Identifier: MPL-2.0

plugins {
    // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm")

    // Apply the java-library plugin for API and implementation separation.
    `java-library`

    // Apply dokka plugin to allow extraction of ducumentation from KDoc comments
    id("org.jetbrains.dokka") version "1.9.20"

    // Make sure we can publish to maven
    `maven-publish`
}

group = "com.alliander"
version = "1.2"

repositories {
    mavenCentral()
}

dependencies {
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    testImplementation("io.kotest:kotest-runner-junit5:4.3.2")
    testImplementation("io.kotest:kotest-assertions-core:4.3.2")
    testImplementation("io.kotest:kotest-property:4.3.2")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of("17"))
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<org.jetbrains.dokka.gradle.DokkaTask>().configureEach {
    outputDirectory.set(buildDir.resolve("dokka"))
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifactId = "open.measure"
            from(components["kotlin"])
        }
    }

    repositories {
        maven {
            name = "AllianderNexus"
            url = uri("https://nexus.apps.ocp-01.prd.ahcaws.com/repository/maven-releases/")
            credentials {
                username = System.getenv("ALLIANDER_NEXUS_USERNAME")
                password = System.getenv("ALLIANDER_NEXUS_PASSWORD")
            }
        }
    }
}
