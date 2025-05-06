// SPDX-FileCopyrightText: 2021-2022 Alliander N.V.
//
// SPDX-License-Identifier: MPL-2.0

package com.alliander.open.measure

import com.alliander.open.measure.Current.Companion.ampere
import com.alliander.open.measure.Energy.Companion.joule
import com.alliander.open.measure.Power.Companion.watt
import com.alliander.open.measure.Time.Companion.seconds
import com.alliander.open.measure.Voltage.Companion.volt
import java.math.BigDecimal

@JvmName("powerTimesTime")
operator fun Measure<Power>.times(duration: Measure<Time>): Measure<Energy> {
    val p = this `in` watt
    val dt = duration `in` seconds
    return Measure(p * dt, joule)
}

@JvmName("currentTimesVoltage")
operator fun Measure<Current>.times(voltage: Measure<Voltage>): Measure<Power> {
    val i = this `in` ampere
    val v = voltage `in` volt
    return Measure(i * v, watt)
}

@JvmName("voltageTimesCurrent")
operator fun Measure<Voltage>.times(current: Measure<Current>): Measure<Power> {
    val v = this `in` volt
    val i = current `in` ampere
    return Measure(i * v, watt)
}

@JvmName("powerDivVoltage")
operator fun Measure<Power>.div(voltage: Measure<Voltage>): Measure<Current> {
    val p = this `in` watt
    val v = voltage `in` volt
    return Measure(p / v, ampere)
}

@JvmName("powerDivCurrent")
operator fun Measure<Power>.div(current: Measure<Current>): Measure<Voltage> {
    val p = this `in` watt
    val i = current `in` ampere
    return Measure(p / i, volt)
}

class Voltage(suffix: String, ratio: BigDecimal = BigDecimal.ONE) : Units(suffix, ratio) {
    companion object {
        val volt = Voltage("V")
        val kiloVolt = Voltage("kV", 1_000.toBigDecimal())
    }
}

class Current(suffix: String, ratio: BigDecimal = BigDecimal.ONE) : Units(suffix, ratio) {
    companion object {
        val ampere = Current("A")
        val kiloAmpere = Current("kA", 1_000.toBigDecimal())
    }
}

class Power(suffix: String, ratio: BigDecimal = BigDecimal.ONE) : Units(suffix, ratio) {
    companion object {
        val watt = Power("W")
        val kiloWatt = Power("kW", 1_000.toBigDecimal())
        val megaWatt = Power("MW", 1_000_000.0.toBigDecimal())
    }
}

class ReactivePower(suffix: String, ratio: BigDecimal = BigDecimal.ONE) : Units(suffix, ratio) {
    companion object {
        val voltAmpereReactive = ReactivePower("VAr", 1.toBigDecimal())
        val kiloVoltAmpereReactive = ReactivePower("kVAr", 1_000.toBigDecimal())
        val megaVoltAmpereReactive = ReactivePower("MVAr", 1_000_000.toBigDecimal())
    }
}

class Energy(suffix: String, ratio: BigDecimal = BigDecimal.ONE) : Units(suffix, ratio) {
    companion object {
        val joule = Energy("J", 1.toBigDecimal())
        val kiloJoule = Energy("kJ", 1_000.toBigDecimal())
        val megaJoule = Energy("MJ", 1_000_000.toBigDecimal())
        val kiloWattHour = Energy("kWh", 3_600_000.toBigDecimal())
        val megaWattHour = Energy("MWh", 3_600_000_000.toBigDecimal())
    }
}

class Time(suffix: String, ratio: BigDecimal = BigDecimal.ONE) : Units(suffix, ratio) {
    companion object {
        val seconds = Time("s", 1.toBigDecimal())
        val minutes = Time("m", 60.toBigDecimal())
        val hours = Time("h", 3_600.toBigDecimal())
    }
}
