// SPDX-FileCopyrightText: 2021-2022 Alliander N.V.
//
// SPDX-License-Identifier: MPL-2.0

package com.alliander.open.measure

import com.alliander.open.measure.Energy.Companion.joule
import com.alliander.open.measure.Energy.Companion.kiloJoule
import com.alliander.open.measure.Energy.Companion.kiloWattHour
import com.alliander.open.measure.Energy.Companion.megaJoule
import com.alliander.open.measure.Energy.Companion.megaWattHour
import com.alliander.open.measure.Power.Companion.kiloWatt
import com.alliander.open.measure.Power.Companion.megaWatt
import com.alliander.open.measure.Power.Companion.watt
import com.alliander.open.measure.Time.Companion.hours
import com.alliander.open.measure.Time.Companion.minutes
import com.alliander.open.measure.Time.Companion.seconds
import com.alliander.open.measure.extension.sum
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.property.checkAll
import java.math.BigDecimal
import java.math.RoundingMode.UP

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

    "isZero returns true when value is zero" {
        val amount = 0000.0000
        val value = amount * kiloWattHour
        val result = value.isZero()

        val expectedResult = true
        result shouldBe expectedResult
    }

    "isZero returns false when value is not zero" {
        val amount = 0000.0000700
        val value = amount * kiloWatt
        val result = value.isZero()

        val expectedResult = false
        result shouldBe expectedResult
    }

    "isNegative works correctly" {
        io.kotest.data.forAll(
                table(
                    headers("value", "expectedResult"),
                    row(-12 * minutes, true),
                    row(0000.0000700 * megaJoule, false),
                    row(0000.0000 * kiloWatt, false),
                    row(100.2 * seconds, false),
                )
        ) { value: Measure<Units>, expectedResult: Boolean ->
            value.isNegative() shouldBe expectedResult
        }
    }

    "isPositive works correctly" {
        io.kotest.data.forAll(
                table(
                    headers("amount", "expectedResult"),
                    row(100.1 * seconds, true),
                    row(-10.1 * joule, false),
                    row(0 * hours, false),
                    row(100.2 * megaWatt, true),
                )
        ) { amount: Measure<Units>, expectedResult: Boolean ->
            amount.isPositive() shouldBe expectedResult
        }
    }

    "energySum returns correct amount of energy when given a list of measures of energy" {
        val list = listOf(100 * joule, 1 * megaWattHour, 1 * megaJoule)
        val result = list.sum()

        val expectedResult = 3601000100 * joule
        result shouldBe expectedResult
    }

    "roundUpToNextMultiple processes different units correctly" {
        io.kotest.data.forAll(
                table(
                    headers("dividend", "divisor", "expectedResult"),
                    row(100.1 * seconds, 15 * minutes, 15 * minutes),
                    row(10.1 * minutes, 10 * seconds, 610 * seconds),
                    row(0.4 * hours, 15 * minutes, 30 * minutes),
                    row(100.2 * seconds, 1.1 * seconds, 101.2 * seconds),
                )
        ) { dividend: Measure<Time>, divisor: Measure<Time>, expectedResult: Measure<Time> ->
            dividend.roundToMultiple(divisor, UP) shouldBe expectedResult
        }
    }

    "Round up positive number to positive non-decimal numbers" {
        io.kotest.data.forAll(
                table(
                    headers("dividend", "divisor", "expectedResult"),
                    row(10.1 * megaWatt, 1 * megaWatt, 11 * megaWatt),
                    row(10.1 * megaWatt, 5 * megaWatt, 15 * megaWatt),
                    row(10.1 * megaWatt, 75 * megaWatt, 75 * megaWatt),
                )
        ) { dividend: Measure<Power>, divisor: Measure<Power>, expectedResult: Measure<Power> ->
            dividend.roundToMultiple(divisor, UP) shouldBe expectedResult
        }
    }

    "Round up positive number to positive decimal numbers" {
        io.kotest.data.forAll(
                table(
                    headers("dividend", "divisor", "expectedResult"),
                    row(10.1 * megaWatt, 0.1 * megaWatt, 10.1 * megaWatt),
                    row(10.1 * megaWatt, 0.5 * megaWatt, 10.5 * megaWatt),
                    row(10.1 * megaWatt, 0.75 * megaWatt, 10.5 * megaWatt),
                )
        ) { dividend: Measure<Power>, divisor: Measure<Power>, expectedResult: Measure<Power> ->
            dividend.roundToMultiple(divisor, UP) shouldBe expectedResult
        }
    }

    "Round up negative number to positive non-decimal numbers" {
        io.kotest.data.forAll(
                table(
                    headers("dividend", "divisor", "expectedResult"),
                    row(-10.1 * megaWatt, 1 * megaWatt, -11 * megaWatt),
                    row(-10.1 * megaWatt, 5 * megaWatt, -15 * megaWatt),
                    row(-10.1 * megaWatt, 75 * megaWatt, -75 * megaWatt),
                )
        ) { dividend: Measure<Power>, divisor: Measure<Power>, expectedResult: Measure<Power> ->
            dividend.roundToMultiple(divisor, UP) shouldBe expectedResult
        }
    }


    "Round up negative number to positive decimal numbers" {
        io.kotest.data.forAll(
                table(
                    headers("dividend", "divisor", "expectedResult"),
                    row(-10.1 * megaWatt, 0.1 * megaWatt, -10.1 * megaWatt),
                    row(-10.1 * megaWatt, 0.5 * megaWatt, -10.5 * megaWatt),
                    row(-10.1 * megaWatt, 0.75 * megaWatt, -10.5 * megaWatt),
                )
        ) { dividend: Measure<Power>, divisor: Measure<Power>, expectedResult: Measure<Power> ->
            dividend.roundToMultiple(divisor, UP) shouldBe expectedResult
        }
    }

    "Zero dividend is properly processed" {
        val result = (0 * megaWatt).roundToMultiple(1 * megaWatt, UP)
        val expectedResult = 0 * megaWatt

        result shouldBe expectedResult
    }

    "Zero divisor is properly processed" {
        val result = (1 * megaWatt).roundToMultiple(0 * megaWatt, UP)
        val expectedResult = 1 * megaWatt

        result shouldBe expectedResult
    }

    "Negative factor is properly processed" {
        val result = (-10.5 * megaWatt).roundToMultiple(-1 * megaWatt, UP)
        val expectedResult = -11 * megaWatt

        result shouldBe expectedResult
    }
})
