package com.alliander.measure.generator.importer

import com.alliander.measure.generator.System

interface Importer {
    fun import(): System
}
