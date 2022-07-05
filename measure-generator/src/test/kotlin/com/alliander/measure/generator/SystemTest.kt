// SPDX-FileCopyrightText: 2021-2022 Alliander N.V.
//
// SPDX-License-Identifier: MPL-2.0

package com.alliander.measure.generator

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
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

    "systems can determine multiplication imports" {
        val system = System(
            listOf(
                Quantity(
                    "Energy", listOf(
                        UnitsDescription("joule", "J", BigDecimal.ONE),
                        UnitsDescription("kiloJoule", "kJ", "1000".toBigDecimal()),
                        UnitsDescription("megaJoule", "MJ", "1000".toBigDecimal()),
                    )
                )
            ),
            listOf(
                Multiplication(
                    Multiplicant("Power", "watt"),
                    Multiplicant("Time", "second"),
                    Multiplicant("Energy", "joule"),
                )
            )
        )

        system.imports() shouldBe listOf(Import("Power", "watt"), Import("Time", "second"), Import("Energy", "joule"))
    }
})
