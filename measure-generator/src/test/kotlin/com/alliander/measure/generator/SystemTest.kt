package com.alliander.measure.generator

import io.kotest.core.spec.style.StringSpec
import java.math.BigDecimal

class SystemTest : StringSpec({
    "systems can be created by hand" {
        val system = System(
            listOf(
                Quantity(
                    "Energy", listOf(
                        UnitsDescription("joule", "J", BigDecimal.ONE),
                        UnitsDescription("kiloJoule", "kJ", "1000".toBigDecimal()),
                        UnitsDescription("megaJoule", "MJ", "1000".toBigDecimal()),
                    )
                )
            )
        )
    }
})
