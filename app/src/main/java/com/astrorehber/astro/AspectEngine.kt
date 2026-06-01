package com.astrorehber.astro

import kotlin.math.abs

enum class AspectType(
    val turkishName: String,
    val angle: Double,
    val orb: Double,
    val isHarmonious: Boolean
) {
    KAVUSUM("Kavuşum", 0.0, 8.0, true),
    KARSITLIK("Karşıtlık", 180.0, 8.0, false),
    UCGEN("Üçgen", 120.0, 7.0, true),
    KARE("Kare", 90.0, 7.0, false),
    SEKSTIL("Sekstil", 60.0, 5.0, true),
    YARIM_KARE("Yarım Kare", 45.0, 2.0, false),
    QUINCUNX("Quincunx", 150.0, 3.0, false)
}

data class Aspect(
    val a: Planet,
    val b: Planet,
    val type: AspectType,
    val orb: Double
)

object AspectEngine {
    fun compute(positions: Map<Planet, PlanetPosition>): List<Aspect> {
        val planets = positions.keys.toList()
        val result = mutableListOf<Aspect>()
        for (i in planets.indices) {
            for (j in i + 1 until planets.size) {
                val pa = positions.getValue(planets[i])
                val pb = positions.getValue(planets[j])
                val diff = abs(shortestArc(pa.longitude, pb.longitude))
                for (type in AspectType.entries) {
                    val orb = abs(diff - type.angle)
                    if (orb <= type.orb) {
                        result += Aspect(planets[i], planets[j], type, orb)
                        break
                    }
                }
            }
        }
        return result.sortedBy { it.orb }
    }
}
