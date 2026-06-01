package com.astrorehber.astro

enum class Planet(val turkishName: String, val glyph: String) {
    GUNES("Güneş", "☉"),
    AY("Ay", "☽"),
    MERKUR("Merkür", "☿"),
    VENUS("Venüs", "♀"),
    MARS("Mars", "♂"),
    JUPITER("Jüpiter", "♃"),
    SATURN("Satürn", "♄"),
    URANUS("Uranüs", "♅"),
    NEPTUN("Neptün", "♆"),
    PLUTO("Plüton", "♇");
}

data class PlanetPosition(
    val planet: Planet,
    val longitude: Double,
    val latitude: Double,
    val distance: Double,
    val retrograde: Boolean
) {
    val sign: ZodiacSign get() = ZodiacSign.fromLongitude(longitude)
    val degreeInSign: Double get() = ZodiacSign.degreeInSign(longitude)
    fun formatted(): String = ZodiacSign.formatLongitude(longitude)
}
