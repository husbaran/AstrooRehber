package com.astrorehber.astro

data class NatalChart(
    val planets: Map<Planet, PlanetPosition>,
    val houses: HouseEngine.HouseChart,
    val aspects: List<Aspect>,
    val sunSign: ZodiacSign,
    val moonSign: ZodiacSign,
    val ascendantSign: ZodiacSign
) {
    val sun: PlanetPosition get() = planets.getValue(Planet.GUNES)
    val moon: PlanetPosition get() = planets.getValue(Planet.AY)

    /** Stable hash used as seed for deterministic daily-advice selection. */
    fun signature(): Long {
        val raw = "${sun.longitude.toInt()}-${moon.longitude.toInt()}-${houses.ascendant.toInt()}"
        var h = 1469598103934665603L
        for (c in raw) {
            h = h xor c.code.toLong()
            h *= 1099511628211L
        }
        return h
    }
}

object NatalChartEngine {
    /**
     * @param jdUt Julian Day in Universal Time (already corrected from local time).
     */
    fun compute(jdUt: Double, latitudeDeg: Double, longitudeDeg: Double): NatalChart {
        val planets = PlanetEngine.allPositions(jdUt)
        val houses = HouseEngine.compute(jdUt, latitudeDeg, longitudeDeg)
        val aspects = AspectEngine.compute(planets)
        return NatalChart(
            planets = planets,
            houses = houses,
            aspects = aspects,
            sunSign = planets.getValue(Planet.GUNES).sign,
            moonSign = planets.getValue(Planet.AY).sign,
            ascendantSign = ZodiacSign.fromLongitude(houses.ascendant)
        )
    }
}
