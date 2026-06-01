package com.astrorehber.data

import com.astrorehber.astro.ZodiacSign
import kotlinx.serialization.Serializable

@Serializable
data class UserProfile(
    val name: String,
    val sunSign: ZodiacSign,
    val moonSign: ZodiacSign,
    val ascendantSign: ZodiacSign,
    val venusSign: ZodiacSign
) {
    /** Stable seed for deterministic daily-advice selection. */
    fun signature(): Long {
        val raw = "${sunSign.name}-${moonSign.name}-${ascendantSign.name}-${venusSign.name}"
        var h = 1469598103934665603L
        for (c in raw) {
            h = h xor c.code.toLong()
            h *= 1099511628211L
        }
        return h
    }
}
