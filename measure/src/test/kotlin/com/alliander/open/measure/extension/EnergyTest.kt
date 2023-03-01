// SPDX-FileCopyrightText: 2021-2022 Alliander N.V.
//
// SPDX-License-Identifier: MPL-2.0

package com.alliander.open.measure.extension

import com.alliander.open.measure.Energy
import com.alliander.open.measure.Energy.Companion.joule
import com.alliander.open.measure.Energy.Companion.kiloJoule
import com.alliander.open.measure.Energy.Companion.megaJoule
import com.alliander.open.measure.Energy.Companion.megaWattHour
import com.alliander.open.measure.Measure
import com.alliander.open.measure.Power
import com.alliander.open.measure.Power.Companion.kiloWatt
import com.alliander.open.measure.Power.Companion.megaWatt
import com.alliander.open.measure.Power.Companion.watt
import com.alliander.open.measure.times
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class EnergyTest : StringSpec({
    "powerSum returns 0 watt when given an empty list" {
        val list = emptyList<Measure<Power>>()

        val result = list.sum()

        val expectedResult = 0 * kiloWatt
        result shouldBe expectedResult
    }

    "powerSum returns correct wattage when given a list of measures of power" {
        val list = listOf(1 * watt, 1 * kiloWatt, 2 * watt, 3 * megaWatt, 4 * watt)
        val result = list.sum()

        val expectedResult = 3001007 * watt
        result shouldBe expectedResult
    }

    "energySum returns 0 joule when given an empty list" {
        val list = emptyList<Measure<Energy>>()
        val result = list.sum()

        val expectedResult = 0 * kiloJoule
        result shouldBe expectedResult
    }

    "energySum returns correct amount of energy when given a list of measures of energy" {
        val list = listOf(100 * joule, 1 * megaWattHour, 1 * megaJoule)
        val result = list.sum()

        val expectedResult = 3601000100 * joule
        result shouldBe expectedResult
    }
})
