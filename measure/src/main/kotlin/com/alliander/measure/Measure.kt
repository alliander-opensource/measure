// SPDX-FileCopyrightText: 2021-2022 Alliander N.V.
//
// SPDX-License-Identifier: MPL-2.0

package com.alliander.measure

import java.math.BigDecimal
import java.math.RoundingMode

operator fun <U : Units> Int.times(units: U): Measure<U> = Measure(this.toBigDecimal(), units)
operator fun <U : Units> Long.times(units: U): Measure<U> = Measure(this.toBigDecimal(), units)
operator fun <U : Units> Double.times(units: U): Measure<U> = Measure(this.toBigDecimal(), units)
operator fun <U : Units> Float.times(units: U): Measure<U> = Measure(this.toBigDecimal(), units)
operator fun <U : Units> BigDecimal.times(units: U): Measure<U> = Measure(this, units)

open class Units(val suffix: String, val ratio: BigDecimal = BigDecimal.ONE) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Units) return false

        if (suffix != other.suffix) return false
        if (ratio != other.ratio) return false

        return true
    }

    override fun hashCode(): Int {
        var result = suffix.hashCode()
        result = 31 * result + ratio.hashCode()
        return result
    }

    override fun toString(): String {
        return suffix
    }
}

operator fun <A : Units, B : A> A.compareTo(other: B): Int = this.ratio.compareTo(other.ratio)
fun <A : Units, B : A> baseUnits(first: A, second: B): A = if (first < second) first else second

data class Measure<U : Units>(val amount: BigDecimal, val units: U) : Comparable<Measure<U>> {
    infix fun <A : U> `as`(other: A): Measure<out U> = if (units == other) this else Measure(this `in` other, other)
    infix fun <A : U> `in`(other: A): BigDecimal =
        if (units == other)
            amount
        else (amount * units.ratio).divide(other.ratio, amount.scale() + units.ratio.precision(), RoundingMode.UP)

    operator fun plus(other: Measure<U>): Measure<U> = baseUnits(
        units,
        other.units
    ).let { Measure((this `in` it) + (other `in` it), it) }

    operator fun minus(other: Measure<U>): Measure<U> = baseUnits(
        units,
        other.units
    ).let { Measure((this `in` it) - (other `in` it), it) }

    operator fun unaryMinus(): Measure<U> = Measure(-amount, units)

    fun abs(): Measure<U> =
        Measure(amount.abs(), units)

    override fun compareTo(other: Measure<U>): Int =
        amount.compareTo((other `as` units).amount)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Measure<*>

        // We say that two measures can only be equal if the two represent the same physical quantity (e.g. power,
        // energy or time). This is represented by the generic type U of the class, which is directly coupled to the
        // class of units.
        if (units.javaClass != other.units.javaClass) return false

        // We now know that they have the same generic type
        other as Measure<U>

        return this.compareTo(other) == 0
    }

    override fun hashCode(): Int {
        var result = units.javaClass.hashCode() // Physical quantity (Dutch: "grootheid", e.g. Power, Energy, Time)
        result = 31 * result + (amount * units.ratio).toBigInteger().hashCode()
        return result
    }

    override fun toString(): String {
        return "$amount$units"
    }
}
