package com.alliander.open.measure

val ZERO_ENERGY = 0 * Energy.joule

@JvmName("sumEnergies")
fun List<Measure<Energy>>.sum(): Measure<Energy> =
        this.fold(ZERO_ENERGY) { value, acc ->
            value + acc
        }
