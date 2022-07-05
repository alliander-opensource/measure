// SPDX-FileCopyrightText: 2021-2022 Alliander N.V.
//
// SPDX-License-Identifier: MPL-2.0

package com.alliander.measure.generator

import com.alliander.measure.generator.exporter.velocity.VelocityExporter
import com.alliander.measure.generator.exporter.velocity.Configuration
import com.alliander.measure.generator.importer.json.JsonImporter
import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.default
import java.io.FileInputStream
import java.io.FileWriter

fun main(args: Array<String>) {
    ArgParser(args).parseInto(::GeneratorArguments).run {
        val importer = JsonImporter(FileInputStream(description))
        val writer = FileWriter(destination)
        val exporter = VelocityExporter(Configuration(packageName, templates), writer)

        exporter.export(importer.import())

        writer.flush()
    }
}

class GeneratorArguments(parser: ArgParser) {
    val packageName by parser.storing("-p", "--packageName", help = "the package name for the generated code" ).default("com.alliander.measure")
    val templates by parser.storing("-t", "--templates", help = "the directory that holds the templates").default("measure-generator/src/main/resources/templates")
    val description by parser.positional("file describing the Units")
    val destination by parser.positional("location where to place the generated code")

}
