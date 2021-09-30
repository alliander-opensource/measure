package com.alliander.measure.generator

import java.math.BigDecimal

val system = System(
    listOf(
        Quantity(
            "Power", listOf(
                UnitsDescription("watt", "W", BigDecimal.ONE),
                UnitsDescription("kiloWatt", "kW", 1_000.toBigDecimal()),
                UnitsDescription("megaWatt", "MW", 1_000_000.toBigDecimal()),
            )
        ),
        Quantity(
            "Time", listOf(
                UnitsDescription("second", "s", BigDecimal.ONE)
            )
        ),
        Quantity(
            "Energy", listOf(
                UnitsDescription("joule", "J", BigDecimal.ONE)
            )
        )
    ),
    listOf(
        Multiplication(Multiplicant("Power", "watt"), Multiplicant("Time", "second"), Multiplicant("Energy", "joule"))
    )
)
