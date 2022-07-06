// SPDX-FileCopyrightText: 2021-2022 Alliander N.V.
//
// SPDX-License-Identifier: MPL-2.0

package com.alliander.measure

import com.alliander.measure.Energy.Companion.joule
import com.alliander.measure.Power.Companion.watt
import com.alliander.measure.Time.Companion.seconds
import java.math.BigDecimal

operator fun Measure<Power>.times(duration: Measure<Time>): Measure<Energy> {
    val p = this `as` watt
    val dt = duration `as` seconds

    return Measure(p.amount * dt.amount, joule)
}

class Power(suffix: String, ratio: BigDecimal = BigDecimal.ONE) : Units(suffix, ratio) {
    companion object {
        val watt = Power("W")
        val kiloWatt = Power("kW", 1_000.toBigDecimal())
        val megaWatt = Power("MW", 1_000_000.0.toBigDecimal())
    }
}

class Energy(suffix: String, ratio: BigDecimal = BigDecimal.ONE) : Units(suffix, ratio) {
    companion object {
        val joule = Energy("J", 1.toBigDecimal())
        val kiloJoule = Energy("kJ", 1_000.toBigDecimal())
        val megaJoule = Energy("MJ", 1_000_000.toBigDecimal())
        val kiloWattHour = Energy("kWh", 3_600_000.toBigDecimal())
    }
}

class Time(suffix: String, ratio: BigDecimal = BigDecimal.ONE) : Units(suffix, ratio) {
    companion object {
        val seconds = Time("s", 1.toBigDecimal())
        val minutes = Time("m", 60.toBigDecimal())
        val hours = Time("h", 3_600.toBigDecimal())
    }
}
