package com.alliander.measure

import java.math.BigDecimal

operator fun <U : Units> Int.times(units: U): Measure<U> = Measure(this.toBigDecimal(), units)
operator fun <U : Units> Long.times(units: U): Measure<U> = Measure(this.toBigDecimal(), units)
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

data class Measure<U : Units>(val amount: BigDecimal, val units: U) {
    infix fun <A : U> `as`(other: A): Measure<out U> = if (units == other) this else Measure(this `in` other, other)
    infix fun <A : U> `in`(other: A): BigDecimal = if (units == other) amount else (amount * units.ratio) / other.ratio

    operator fun plus(other: Measure<U>): Measure<U> = baseUnits(
        units,
        other.units
    ).let { Measure((this `in` it) + (other `in` it), it) }

    operator fun minus(other: Measure<U>): Measure<U> = baseUnits(
        units,
        other.units
    ).let { Measure((this `in` it) - (other `in` it), it) }

    operator fun unaryMinus(): Measure<U> = Measure(-amount, units)

    override fun toString(): String {
        return "$amount$units"
    }
}
