package com.astrorehber.astro

import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.tan

/**
 * Whole-sign house system: most robust under approximate input and at
 * extreme latitudes. The Ascendant's sign becomes the 1st house cusp at 0°,
 * subsequent houses follow each next sign. The exact ASC degree is preserved.
 */
object HouseEngine {

    data class HouseChart(
        val ascendant: Double,
        val midheaven: Double,
        val cusps: List<Double>
    ) {
        fun houseOfLongitude(longitudeDeg: Double): Int {
            val ascSign = ZodiacSign.fromLongitude(ascendant).ordinal
            val planetSign = ZodiacSign.fromLongitude(longitudeDeg).ordinal
            val h = ((planetSign - ascSign + 12) % 12) + 1
            return h
        }
    }

    /**
     * Compute the Ascendant (eastern horizon's ecliptic longitude) and MC,
     * then derive whole-sign cusps. Reference: Meeus Ch. 12, 21.
     *
     * @param jdUt Julian day in UT.
     * @param latitudeDeg geographic latitude (north +).
     * @param longitudeDeg geographic longitude (east +).
     */
    fun compute(jdUt: Double, latitudeDeg: Double, longitudeDeg: Double): HouseChart {
        val d = daysSinceJ2000(jdUt)
        val eps = obliquity(d)

        // Local sidereal time in degrees.
        val lst = localSiderealTime(jdUt, longitudeDeg)
        val ramcDeg = lst                                  // Right Ascension of MC
        val ramcRad = ramcDeg.deg()
        val epsRad = eps.deg()
        val latRad = latitudeDeg.deg()

        // Midheaven (MC) ecliptic longitude.
        val mc = normalizeDegrees(
            atan2(sin(ramcRad), cos(ramcRad) * cos(epsRad)).rad()
        )

        // Ascendant (Meeus 12.7-ish form, with quadrant fix).
        val ascNum = -cos(ramcRad)
        val ascDen = sin(epsRad) * tan(latRad) + cos(epsRad) * sin(ramcRad)
        var asc = atan2(ascNum, ascDen).rad()
        asc = normalizeDegrees(asc)
        // Make sure ASC is on the eastern half (opposite the MC).
        val diff = shortestArc(mc, asc)
        if (diff < 0) asc = normalizeDegrees(asc + 180.0)

        val ascSign = ZodiacSign.fromLongitude(asc)
        val cusps = (0 until 12).map { i ->
            normalizeDegrees(ascSign.ordinal * 30.0 + i * 30.0)
        }

        return HouseChart(asc, mc, cusps)
    }

    /** Greenwich Sidereal Time then converted to local, in degrees. Meeus Ch. 12. */
    private fun localSiderealTime(jdUt: Double, longitudeDeg: Double): Double {
        val t = (jdUt - 2451545.0) / 36525.0
        var gmst = 280.46061837 +
                360.98564736629 * (jdUt - 2451545.0) +
                0.000387933 * t * t -
                t * t * t / 38710000.0
        gmst = normalizeDegrees(gmst)
        return normalizeDegrees(gmst + longitudeDeg)
    }
}
