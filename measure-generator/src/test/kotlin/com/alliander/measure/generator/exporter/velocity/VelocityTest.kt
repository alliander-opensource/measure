// SPDX-FileCopyrightText: 2021-2022 Alliander N.V.
//
// SPDX-License-Identifier: MPL-2.0

package com.alliander.measure.generator.exporter.velocity

import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.StringSpec
import io.kotest.core.test.TestCase
import io.kotest.matchers.shouldBe
import org.apache.velocity.VelocityContext
import org.apache.velocity.app.VelocityEngine
import java.io.StringWriter
import java.io.Writer
import java.util.Properties

class VelocityTest : StringSpec() {
    lateinit var engine: VelocityEngine
    lateinit var writer: Writer

    override fun beforeSpec(spec: Spec) {
        super.beforeSpec(spec)
        val properties = Properties()
        properties.setProperty("file.resource.loader.path", "src/test/resources/templates")
        engine = VelocityEngine()
        engine.init(properties)
    }

    override fun beforeEach(testCase: TestCase) {
        super.beforeEach(testCase)
        writer = StringWriter()
    }

    init {
        "templates can be read from resources" {
            val template = engine.getTemplate("hello_world.vtl")
            val context = VelocityContext()

            template.merge(context, writer)

            writer.toString() shouldBe "Hello, World!\n"
        }

        "templates use contexts to fill data" {
            val template = engine.getTemplate("hello_subject.vtl")
            val context = VelocityContext()
            context.put("subject", "World")

            template.merge(context, writer)

            writer.toString() shouldBe "Hello, World!\n"
        }

        "templates use contexts to fill data from objects" {
            val template = engine.getTemplate("hello_object.vtl")
            val context = VelocityContext()
            context.put("subject", Subject("World"))

            template.merge(context, writer)

            writer.toString() shouldBe "Hello, World!\n"
        }


        "templates use contexts to fill data from nested objects" {
            val template = engine.getTemplate("hello_nested.vtl")
            val context = VelocityContext()
            context.put("message", Message(Subject("World")))

            template.merge(context, writer)

            writer.toString() shouldBe "Hello, World!\n"
        }
    }
}

data class Message(val subject: Subject)

data class Subject(val name: String)
