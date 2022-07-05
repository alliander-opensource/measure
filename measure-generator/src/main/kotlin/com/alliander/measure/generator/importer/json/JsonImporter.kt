// SPDX-FileCopyrightText: 2021-2022 Alliander N.V.
//
// SPDX-License-Identifier: MPL-2.0

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
