// SPDX-FileCopyrightText: 2021-2022 Alliander N.V.
//
// SPDX-License-Identifier: MPL-2.0

import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
    kotlin("jvm")

    // Apply the java-library plugin for API and implementation separation.
    `java-library`

    // Apply dokka plugin to allow extraction of ducumentation from KDoc comments
    id("org.jetbrains.dokka") version "1.9.20"

    id("com.vanniktech.maven.publish") version "0.30.0"
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

    testImplementation("io.kotest:kotest-runner-junit5:5.9.1")
    testImplementation("io.kotest:kotest-assertions-core:5.9.1")
    testImplementation("io.kotest:kotest-property:5.9.1")
}

kotlin {
    jvmToolchain(17)
}

java {
    // We add both the sources and the javadoc jar.
    withSourcesJar()
    withJavadocJar()
}

// This task is added by Gradle when we use java.withJavadocJar()
val javadocJar = tasks.named<Jar>("javadocJar") {
    from(tasks.named("dokkaJavadoc"))
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<DokkaTask>().configureEach {
    outputDirectory.set(buildDir.resolve("dokka"))
}

mavenPublishing {
    // Publishing to https://s01.oss.sonatype.org
    publishToMavenCentral(SonatypeHost.S01)
    signAllPublications()
    coordinates("com.alliander", "measure", "1.3.0-SNAPSHOT")

    pom {
        name.set("Measure")
        description.set("Measure is a Kotlin library for working with units of measurement that are - for example - used in the power system sector.")
        url.set("https://github.com/alliander-opensource/measure")

        licenses {
            license {
                name.set("Mozilla Public License Version 2.0")
                url.set("https://www.mozilla.org/en-US/MPL/2.0/")
            }
        }
        developers {
            developer {
                id.set("dvberkel")
                name.set("Daan van Berkel")
                email.set("daan.v.berkel.1980@gmail.com")
            }
            developer {
                id.set("gmulders")
                name.set("Geert Mulders")
                email.set("gmulders@gmail.com")
            }
        }
        scm {
            url.set("https://github.com/alliander-opensource/measure")
        }
    }
}
