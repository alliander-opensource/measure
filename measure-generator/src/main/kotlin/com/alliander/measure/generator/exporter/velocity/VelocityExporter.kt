// SPDX-FileCopyrightText: 2021-2022 Alliander N.V.
//
// SPDX-License-Identifier: MPL-2.0

package com.alliander.measure.generator.exporter.velocity

import com.alliander.measure.generator.System
import com.alliander.measure.generator.exporter.Exporter
import org.apache.velocity.Template
import org.apache.velocity.VelocityContext
import org.apache.velocity.app.VelocityEngine
import java.io.Writer
import java.util.Properties

data class VelocityExporter(private val configuration: Configuration, private val writer: Writer) : Exporter {
    private val template: Template

    init {
        val properties = Properties()
        properties.setProperty("file.resource.loader.path", configuration.templatePath)
        val engine = VelocityEngine()
        engine.init(properties)
        template = engine.getTemplate("specific.vtl")
    }

    override fun export(system: System) {
        val context = VelocityContext()
        context.put("configuration", configuration)
        context.put("system", system)
        template.merge(context, writer)
    }
}

data class Configuration(val packageName: String, val templatePath: String)
