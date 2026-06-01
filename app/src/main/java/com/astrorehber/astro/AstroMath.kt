package com.astrorehber.astro

import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.floor
import kotlin.math.sin
import kotlin.math.sqrt

internal const val DEG_TO_RAD = PI / 180.0
internal const val RAD_TO_DEG = 180.0 / PI

internal fun Double.deg(): Double = this * DEG_TO_RAD
internal fun Double.rad(): Double = this * RAD_TO_DEG

internal fun normalizeDegrees(value: Double): Double {
    var d = value % 360.0
    if (d < 0) d += 360.0
    return d
}

internal fun shortestArc(a: Double, b: Double): Double {
    var diff = ((b - a) % 360.0 + 540.0) % 360.0 - 180.0
    return diff
}

/**
 * Julian Day Number at UT for a given calendar date/time.
 * Formula: Jean Meeus, "Astronomical Algorithms" Ch. 7.
 */
fun julianDayUt(
    year: Int, month: Int, day: Int,
    hour: Int, minute: Int, second: Int = 0
): Double {
    var y = year
    var m = month
    if (m <= 2) { y -= 1; m += 12 }
    val a = floor(y / 100.0)
    val b = 2 - a + floor(a / 4.0)
    val dayFraction = (hour + minute / 60.0 + second / 3600.0) / 24.0
    return floor(365.25 * (y + 4716)) +
            floor(30.6001 * (m + 1)) +
            day + dayFraction + b - 1524.5
}

/** Days since 2000-01-01 00:00 UT (epoch used by Paul Schlyter's formulas). */
internal fun daysSinceJ2000(jdUt: Double): Double = jdUt - 2451545.0

/** Mean obliquity of ecliptic in degrees (IAU 1980 formula, sufficient for app). */
internal fun obliquity(d: Double): Double = 23.4393 - 3.563e-7 * d

/** Solve Kepler's equation E - e*sin(E) = M (E,M in degrees). Returns E in degrees. */
internal fun solveKepler(mDeg: Double, e: Double): Double {
    val mRad = normalizeDegrees(mDeg).deg()
    var eRad = mRad + e * sin(mRad) * (1.0 + e * cos(mRad))
    repeat(8) {
        val d0 = eRad - e * sin(eRad) - mRad
        val d1 = 1.0 - e * cos(eRad)
        eRad -= d0 / d1
    }
    return eRad.rad()
}

internal data class HelioCoord(val x: Double, val y: Double, val z: Double) {
    fun toEcliptic(): EclCoord {
        val lon = normalizeDegrees(atan2(y, x).rad())
        val lat = atan2(z, sqrt(x * x + y * y)).rad()
        val dist = sqrt(x * x + y * y + z * z)
        return EclCoord(lon, lat, dist)
    }
}

internal data class EclCoord(val longitude: Double, val latitude: Double, val distance: Double)
