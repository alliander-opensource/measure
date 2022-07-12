// SPDX-FileCopyrightText: 2021-2022 Alliander N.V.
//
// SPDX-License-Identifier: MPL-2.0

package com.alliander.open.measure.generator.exporter

import com.alliander.open.measure.generator.System

interface Exporter {
    fun export(system: System)
}
