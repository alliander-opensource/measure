// SPDX-FileCopyrightText: 2021-2022 Alliander N.V.
//
// SPDX-License-Identifier: MPL-2.0

package com.alliander.open.measure

import com.alliander.open.measure.Current.Companion.ampere
import com.alliander.open.measure.Current.Companion.kiloAmpere
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
import com.alliander.open.measure.Voltage.Companion.kiloVolt
import com.alliander.open.measure.Voltage.Companion.volt
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.beInstanceOf

class SpecificUnitsTest : StringSpec({
    "The units have the correct measure" {
        joule should beInstanceOf<Energy>()
        kiloJoule should beInstanceOf<Energy>()
        megaJoule should beInstanceOf<Energy>()
        kiloWattHour should beInstanceOf<Energy>()
        megaWattHour should beInstanceOf<Energy>()

        watt should beInstanceOf<Power>()
        kiloWatt should beInstanceOf<Power>()
        megaWatt should beInstanceOf<Power>()

        seconds should beInstanceOf<Time>()
        minutes should beInstanceOf<Time>()
        hours should beInstanceOf<Time>()

        volt should beInstanceOf<Voltage>()
        kiloVolt should beInstanceOf<Voltage>()

        ampere should beInstanceOf<Current>()
        kiloAmpere should beInstanceOf<Current>()
    }

    "power times time is energy" {
        val power = 10 * kiloWatt
        val duration = 15 * minutes

        val energy = power * duration

        energy `as` megaJoule shouldBe 9 * megaJoule
    }

    "current times voltage is power" {
        val current = 10 * ampere
        val voltage = 15 * volt

        val power = current * voltage

        power `as` watt shouldBe 150 * watt
    }

    "voltage times current is power" {
        val current = 10 * ampere
        val voltage = 15 * volt

        val power = voltage * current

        power `as` watt shouldBe 150 * watt
    }

    "power divided by voltage is current" {
        val power = 150 * watt
        val voltage = 15 * volt

        val current = power / voltage

        current `as` ampere shouldBe 10 * ampere
    }

    "power divided by current is voltage" {
        val power = 150 * watt
        val current = 10 * ampere

        val voltage = power / current

        voltage `as` volt shouldBe 15 * volt
    }
})
