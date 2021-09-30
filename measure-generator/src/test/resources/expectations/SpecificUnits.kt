package com.alliander.test

import java.math.BigDecimal

class Power(suffix: String, ratio: BigDecimal = BigDecimal.ONE) : Units(suffix, ratio) {
    companion object {
        val watt = Power("W", "1".toBigDecimal())
        val kiloWatt = Power("kW", "1000".toBigDecimal())
        val megaWatt = Power("MW", "1000000".toBigDecimal())
    }
}

