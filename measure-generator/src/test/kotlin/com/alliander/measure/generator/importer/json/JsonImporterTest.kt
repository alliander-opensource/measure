// SPDX-FileCopyrightText: 2021-2022 Alliander N.V.
//
// SPDX-License-Identifier: MPL-2.0

package com.alliander.measure.generator.importer.json

import com.alliander.measure.generator.system
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.io.FileInputStream

class JsonImporterTest: StringSpec({
    "systems are correctly imported" {
        val input = FileInputStream("src/test/resources/systems/json/default_system.json")
        val importer = JsonImporter(input)

        val actual = importer.import()

        actual shouldBe system
    }
})
