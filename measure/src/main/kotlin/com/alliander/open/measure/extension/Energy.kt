// SPDX-FileCopyrightText: 2021-2022 Alliander N.V.
//
// SPDX-License-Identifier: MPL-2.0

package com.alliander.open.measure.extension

import com.alliander.open.measure.Energy
import com.alliander.open.measure.Measure
import com.alliander.open.measure.times

val ZERO_ENERGY = 0 * Energy.joule

@JvmName("sumEnergies")
fun List<Measure<Energy>>.sum(): Measure<Energy> =
        this.fold(ZERO_ENERGY) { value, acc ->
            value + acc
        }
