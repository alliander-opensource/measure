package com.alliander.measure.generator.importer.json

import com.alliander.measure.generator.System
import com.alliander.measure.generator.importer.Importer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.InputStream

data class JsonImporter(val input: InputStream): Importer {
    override fun import(): System {
        return Json.decodeFromStream(input)
    }
}
