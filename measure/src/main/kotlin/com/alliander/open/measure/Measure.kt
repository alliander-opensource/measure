// SPDX-FileCopyrightText: 2021-2022 Alliander N.V.
//
// SPDX-License-Identifier: MPL-2.0

package com.alliander.open.measure

import java.math.BigDecimal
import java.math.RoundingMode

operator fun <U : Units> Int.times(units: U): Measure<U> = Measure(this.toBigDecimal(), units)
operator fun <U : Units> Long.times(units: U): Measure<U> = Measure(this.toBigDecimal(), units)
operator fun <U : Units> Double.times(units: U): Measure<U> = Measure(this.toBigDecimal(), units)
operator fun <U : Units> Float.times(units: U): Measure<U> = Measure(this.toBigDecimal(), units)
operator fun <U : Units> BigDecimal.times(units: U): Measure<U> = Measure(this, units)

/**
 * [Units] is the base class for a Unit, e.g. [Power] or [Current]. The reason for calling it "Units" is that "Unit"
 * was taken in Kotlin.
 */
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
    infix fun <A : U> `as`(other: A): Measure<U> = if (units == other) this else Measure(this `in` other, other)
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

    fun sign(): Int = this.amount.signum()

    fun isZero(): Boolean = this.sign() == 0

    fun isNegative(): Boolean = this.sign() == -1

    fun isPositive(): Boolean = this.sign() == 1

    /**
     * Rounds this [Measure] (dividend) to the next multiple of the specified [Measure] (factor).
     * Given the rounding Mode is UP; The rounding type is 'round-away-from-zero',
     * meaning that 10.1 rounded to a factor 1 is 11, and -10.1 rounded to a factor 1 is -11.
     * Given the rounding Mode is DOWN; The rounding type is 'round-towards-zero',
     * meaning that 10.1 rounded to a factor 1 is 10, and -10.1 rounded to a factor 1 is -10.
     * - In case the dividend or the factor is zero, the dividend is returned as is.
     * - In case the factor is negative, it is assessed as if it were positive.
     */
    fun roundToMultiple(factor: Measure<U>, roundingMode: RoundingMode): Measure<U> {
        if (this.isZero() || factor.isZero()) {
            return this
        }
        val base = baseUnits(this.units, factor.units)
        val dividend = this `in` base
        val absoluteFactor = (factor `in` base).abs()
        return dividend.roundToMultiple(absoluteFactor, roundingMode) * base
    }

}

private fun BigDecimal.roundToMultiple(factor: BigDecimal, roundingMode: RoundingMode): BigDecimal =
        this.divide(factor, 0, roundingMode) * factor
