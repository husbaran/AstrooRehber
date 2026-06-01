package com.astrorehber.data

import kotlinx.serialization.Serializable

@Serializable
data class City(
    val name: String,
    val country: String,
    val latitude: Double,
    val longitude: Double,
    val timeZoneId: String
) {
    val displayName: String
        get() = if (country == "Türkiye") name else "$name, $country"
}
