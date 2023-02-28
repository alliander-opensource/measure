package com.alliander.open.measure

val ZERO_POWER = 0 * Power.watt

@JvmName("sumPowers")
fun List<Measure<Power>>.sum(): Measure<Power> =
        this.fold(ZERO_POWER) { value, acc ->
            value + acc
        }
