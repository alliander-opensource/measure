// SPDX-FileCopyrightText: 2021-2022 Alliander N.V.
//
// SPDX-License-Identifier: MPL-2.0

package com.alliander.measure

import com.alliander.measure.Energy.Companion.joule
import com.alliander.measure.Energy.Companion.kiloJoule
import com.alliander.measure.Energy.Companion.kiloWattHour
import com.alliander.measure.Energy.Companion.megaJoule
import com.alliander.measure.Power.Companion.kiloWatt
import com.alliander.measure.Power.Companion.watt
import com.alliander.measure.Time.Companion.minutes
import com.alliander.measure.Time.Companion.seconds
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.property.checkAll
import java.math.BigDecimal

class MeasureTest : StringSpec({
    "Joule, Watt and seconds are different units" {
        joule shouldNotBe watt
        joule shouldNotBe seconds

        watt shouldNotBe joule
        watt shouldNotBe seconds

        seconds shouldNotBe joule
        seconds shouldNotBe watt
    }

    "joules and kiloJoules can be added" {
        val left = 1000 * joule
        val right = 1 * kiloJoule

        val sum = (left + right) `as` joule

        sum shouldBe 2000 * joule
    }

    "power times time is energy" {
        val power = 10 * kiloWatt
        val duration = 15 * minutes

        val energy = power * duration

        energy `as` megaJoule shouldBe 9 * megaJoule
    }

    "joules can be added" {
        checkAll { leftQuantity: Int, rightQuantity: Int ->
            val left = leftQuantity * joule
            val right = rightQuantity * joule

            val sum = left + right

            val expectedQuantity = (leftQuantity.toLong()) + (rightQuantity.toLong())
            sum shouldBe expectedQuantity * joule
        }
    }

    "support decimals" {
        val value = 3905.07

        val result = value * kiloWattHour

        val expectedResult = Measure(BigDecimal.valueOf(value), kiloWattHour)
        result shouldBe expectedResult
    }
})
