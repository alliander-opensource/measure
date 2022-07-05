// SPDX-FileCopyrightText: 2021-2022 Alliander N.V.
//
// SPDX-License-Identifier: MPL-2.0

package com.alliander.measure.generator.exporter.velocity

import com.alliander.measure.generator.exporter.Exporter
import com.alliander.measure.generator.system
import io.kotest.core.spec.style.StringSpec
import io.kotest.core.test.TestCase
import io.kotest.matchers.shouldBe
import java.io.File
import java.io.StringWriter
import java.io.Writer

class VelocityExporterTest : StringSpec() {
    lateinit var writer: Writer

    override fun beforeEach(testCase: TestCase) {
        super.beforeEach(testCase)
        writer = StringWriter()
    }

    init {
        "systems are correctly exported" {
            val exporter: Exporter = VelocityExporter(Configuration(
                packageName = "com.alliander.test",
                templatePath = "src/main/resources/templates"
            ), writer)
            val expected = File("src/test/resources/expectations/SpecificUnits.expectation").readText()

            exporter.export(system)

            val actual = writer.toString()
            actual shouldBe expected
        }
    }
}

