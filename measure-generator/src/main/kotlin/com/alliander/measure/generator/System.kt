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
data class System(val quantities: Collection<Quantity>)

@Serializable
data class Quantity(val name: String, val conversions: Collection<UnitsDescription>)

@Serializable
data class UnitsDescription(val name: String, val suffix: String,
                            @Serializable(with=BigDecimalAsStringSerializer::class)
                            val ratio: BigDecimal)

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
