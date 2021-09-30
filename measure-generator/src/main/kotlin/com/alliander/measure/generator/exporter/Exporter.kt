package com.alliander.measure.generator.exporter

import com.alliander.measure.generator.System

interface Exporter {
    fun export(system: System)
}
