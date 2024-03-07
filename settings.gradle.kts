// SPDX-FileCopyrightText: 2021-2022 Alliander N.V.
//
// SPDX-License-Identifier: MPL-2.0

/*
 * This file was generated by the Gradle 'init' task.
 *
 * The settings file is used to specify which projects to include in your build.
 *
 * Detailed information about configuring a multi-project build in Gradle can be found
 * in the user manual at https://docs.gradle.org/6.7.1/userguide/multi_project_builds.html
 */

rootProject.name = "measure"
include("measure")
include("measure-generator")

pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}
