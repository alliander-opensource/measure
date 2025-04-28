// SPDX-FileCopyrightText: 2024-2025 Alliander N.V.
//
// SPDX-License-Identifier: MPL-2.0

package com.alliander.open.measure.extension

import com.alliander.open.measure.Measure
import com.alliander.open.measure.ReactivePower
import com.alliander.open.measure.times

val ZERO_REACTIVE_POWER = 0 * ReactivePower.voltAmpereReactive

@JvmName("sumReactivePowers")
fun List<Measure<ReactivePower>>.sum(): Measure<ReactivePower> =
    this.fold(ZERO_REACTIVE_POWER) { value, acc ->
        value + acc
    }