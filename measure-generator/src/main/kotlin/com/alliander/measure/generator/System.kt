// SPDX-FileCopyrightText: 2021-2022 Alliander N.V.
//
// SPDX-License-Identifier: MPL-2.0

package com.alliander.measure.generator

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.math.BigDecimal

@Serializable
data class System(val quantities: Collection<Quantity>, val multiplications: Collection<Multiplication> = emptyList()) {
    fun imports(): Collection<Import> =
        multiplications
            .toSet()
            .flatMap { m -> setOf(m.left, m.right, m.to) }
            .map(Multiplicant::toImport)

}

@Serializable
data class Quantity(val name: String, val conversions: Collection<UnitsDescription>)

@Serializable
data class UnitsDescription(
    val name: String, val suffix: String,
    @Serializable(with = BigDecimalAsStringSerializer::class)
    val ratio: BigDecimal
)

object BigDecimalAsStringSerializer : KSerializer<BigDecimal> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("BigDecimal", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): BigDecimal {
        val input = decoder.decodeString()
        return BigDecimal(input)
    }

    override fun serialize(encoder: Encoder, value: BigDecimal) {
        encoder.encodeString(value.toPlainString())
    }
}

@Serializable
data class Multiplication(val left: Multiplicant, val right: Multiplicant, val to: Multiplicant)

@Serializable
data class Multiplicant(val unit: String, val base: String) {
    fun toImport(): Import =
        Import(unit, base)
}

data class Import(val unit: String, val base: String)
