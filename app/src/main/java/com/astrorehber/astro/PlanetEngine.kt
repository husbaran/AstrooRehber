package com.astrorehber.astro

import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

/**
 * Planet position engine based on Paul Schlyter's elementary orbital algorithm
 * (http://www.stjarnhimlen.se/comp/ppcomp.html). Accuracy: roughly 1' for Sun,
 * a few arc-minutes for inner planets, ~0.5° for outer planets. Sufficient
 * for natal-chart display and daily-guidance scope.
 */
object PlanetEngine {

    private data class Elements(
        val n0: Double, val nDot: Double,    // longitude of ascending node
        val i0: Double, val iDot: Double,    // inclination
        val w0: Double, val wDot: Double,    // argument of perihelion
        val a0: Double, val aDot: Double,    // semi-major axis
        val e0: Double, val eDot: Double,    // eccentricity
        val m0: Double, val mDot: Double     // mean anomaly
    )

    private val elements = mapOf(
        Planet.GUNES to Elements(
            0.0, 0.0,
            0.0, 0.0,
            282.9404, 4.70935e-5,
            1.0, 0.0,
            0.016709, -1.151e-9,
            356.0470, 0.9856002585
        ),
        Planet.AY to Elements(
            125.1228, -0.0529538083,
            5.1454, 0.0,
            318.0634, 0.1643573223,
            60.2666, 0.0,
            0.054900, 0.0,
            115.3654, 13.0649929509
        ),
        Planet.MERKUR to Elements(
            48.3313, 3.24587e-5,
            7.0047, 5.00e-8,
            29.1241, 1.01444e-5,
            0.387098, 0.0,
            0.205635, 5.59e-10,
            168.6562, 4.0923344368
        ),
        Planet.VENUS to Elements(
            76.6799, 2.46590e-5,
            3.3946, 2.75e-8,
            54.8910, 1.38374e-5,
            0.723330, 0.0,
            0.006773, -1.302e-9,
            48.0052, 1.6021302244
        ),
        Planet.MARS to Elements(
            49.5574, 2.11081e-5,
            1.8497, -1.78e-8,
            286.5016, 2.92961e-5,
            1.523688, 0.0,
            0.093405, 2.516e-9,
            18.6021, 0.5240207766
        ),
        Planet.JUPITER to Elements(
            100.4542, 2.76854e-5,
            1.3030, -1.557e-7,
            273.8777, 1.64505e-5,
            5.20256, 0.0,
            0.048498, 4.469e-9,
            19.8950, 0.0830853001
        ),
        Planet.SATURN to Elements(
            113.6634, 2.38980e-5,
            2.4886, -1.081e-7,
            339.3939, 2.97661e-5,
            9.55475, 0.0,
            0.055546, -9.499e-9,
            316.9670, 0.0334442282
        ),
        Planet.URANUS to Elements(
            74.0005, 1.3978e-5,
            0.7733, 1.9e-8,
            96.6612, 3.0565e-5,
            19.18171, -1.55e-8,
            0.047318, 7.45e-9,
            142.5905, 0.011725806
        ),
        Planet.NEPTUN to Elements(
            131.7806, 3.0173e-5,
            1.7700, -2.55e-7,
            272.8461, -6.027e-6,
            30.05826, 3.313e-8,
            0.008606, 2.15e-9,
            260.2471, 0.005995147
        ),
        // Pluto: simplified mean elements + linear mean motion (period 247.94y).
        Planet.PLUTO to Elements(
            110.30, 0.0,
            17.16, 0.0,
            113.76, 0.0,
            39.482, 0.0,
            0.2488, 0.0,
            14.53, 360.0 / (247.94 * 365.25)
        )
    )

    /** Compute one planet's geocentric ecliptic position at a given Julian Day (UT). */
    fun position(planet: Planet, jdUt: Double): PlanetPosition {
        val d = daysSinceJ2000(jdUt)
        val cur = compute(planet, d)
        // Detect retrograde by sampling 1 day earlier.
        val prev = compute(planet, d - 1.0)
        val delta = shortestArc(prev.longitude, cur.longitude)
        return PlanetPosition(
            planet = planet,
            longitude = cur.longitude,
            latitude = cur.latitude,
            distance = cur.distance,
            retrograde = delta < 0
        )
    }

    fun allPositions(jdUt: Double): Map<Planet, PlanetPosition> =
        Planet.entries.associateWith { position(it, jdUt) }

    // --- internal ---

    private fun compute(planet: Planet, d: Double): EclCoord = when (planet) {
        Planet.GUNES -> sunPosition(d)
        Planet.AY -> moonPosition(d)
        else -> generalPlanetPosition(planet, d)
    }

    private fun el(planet: Planet, d: Double): EvaluatedElements {
        val e = elements.getValue(planet)
        return EvaluatedElements(
            n = normalizeDegrees(e.n0 + e.nDot * d),
            i = e.i0 + e.iDot * d,
            w = normalizeDegrees(e.w0 + e.wDot * d),
            a = e.a0 + e.aDot * d,
            e = e.e0 + e.eDot * d,
            m = normalizeDegrees(e.m0 + e.mDot * d)
        )
    }

    private data class EvaluatedElements(
        val n: Double, val i: Double, val w: Double,
        val a: Double, val e: Double, val m: Double
    )

    private fun sunPosition(d: Double): EclCoord {
        val s = el(Planet.GUNES, d)
        val eAnom = solveKepler(s.m, s.e)
        val xv = cos(eAnom.deg()) - s.e
        val yv = sqrt(1.0 - s.e * s.e) * sin(eAnom.deg())
        val v = Math.toDegrees(Math.atan2(yv, xv))
        val r = sqrt(xv * xv + yv * yv)
        val lon = normalizeDegrees(v + s.w)
        return EclCoord(lon, 0.0, r)
    }

    private fun moonPosition(d: Double): EclCoord {
        val moon = el(Planet.AY, d)
        val sun = el(Planet.GUNES, d)

        // Moon heliocentric (well — geocentric here, since Moon orbits Earth).
        val eAnom = solveKepler(moon.m, moon.e)
        val xv = moon.a * (cos(eAnom.deg()) - moon.e)
        val yv = moon.a * sqrt(1.0 - moon.e * moon.e) * sin(eAnom.deg())
        val v = Math.toDegrees(Math.atan2(yv, xv))
        val r = sqrt(xv * xv + yv * yv)

        val nRad = moon.n.deg()
        val viw = (v + moon.w).deg()
        val iRad = moon.i.deg()

        val xh = r * (cos(nRad) * cos(viw) - sin(nRad) * sin(viw) * cos(iRad))
        val yh = r * (sin(nRad) * cos(viw) + cos(nRad) * sin(viw) * cos(iRad))
        val zh = r * (sin(viw) * sin(iRad))

        var lon = normalizeDegrees(Math.toDegrees(Math.atan2(yh, xh)))
        var lat = Math.toDegrees(Math.atan2(zh, sqrt(xh * xh + yh * yh)))

        // Main lunar perturbations (Schlyter), in degrees.
        val lSun = normalizeDegrees(sun.m + sun.w)
        val lMoon = normalizeDegrees(moon.n + moon.w + moon.m)
        val mMoon = moon.m
        val mSun = sun.m
        val dArg = lMoon - lSun
        val f = lMoon - moon.n

        lon += -1.274 * sin((mMoon - 2 * dArg).deg())
        lon += 0.658 * sin((2 * dArg).deg())
        lon += -0.186 * sin(mSun.deg())
        lon += -0.059 * sin((2 * mMoon - 2 * dArg).deg())
        lon += -0.057 * sin((mMoon - 2 * dArg + mSun).deg())
        lon += 0.053 * sin((mMoon + 2 * dArg).deg())
        lon += 0.046 * sin((2 * dArg - mSun).deg())
        lon += 0.041 * sin((mMoon - mSun).deg())
        lon += -0.035 * sin(dArg.deg())
        lon += -0.031 * sin((mMoon + mSun).deg())

        lat += -0.173 * sin((f - 2 * dArg).deg())
        lat += -0.055 * sin((mMoon - f - 2 * dArg).deg())
        lat += -0.046 * sin((mMoon + f - 2 * dArg).deg())
        lat += 0.033 * sin((f + 2 * dArg).deg())
        lat += 0.017 * sin((2 * mMoon + f).deg())

        lon = normalizeDegrees(lon)
        return EclCoord(lon, lat, r)
    }

    private fun generalPlanetPosition(planet: Planet, d: Double): EclCoord {
        val p = el(planet, d)
        val s = sunPosition(d)

        val eAnom = solveKepler(p.m, p.e)
        val xv = p.a * (cos(eAnom.deg()) - p.e)
        val yv = p.a * sqrt(1.0 - p.e * p.e) * sin(eAnom.deg())
        val v = Math.toDegrees(Math.atan2(yv, xv))
        val r = sqrt(xv * xv + yv * yv)

        val nRad = p.n.deg()
        val viw = (v + p.w).deg()
        val iRad = p.i.deg()

        // Heliocentric ecliptic coords.
        val xh = r * (cos(nRad) * cos(viw) - sin(nRad) * sin(viw) * cos(iRad))
        val yh = r * (sin(nRad) * cos(viw) + cos(nRad) * sin(viw) * cos(iRad))
        val zh = r * (sin(viw) * sin(iRad))

        // Sun geocentric -> add to planet helio for geocentric position.
        val lonSunRad = s.longitude.deg()
        val xs = s.distance * cos(lonSunRad)
        val ys = s.distance * sin(lonSunRad)

        val xg = xh + xs
        val yg = yh + ys
        val zg = zh

        val lon = normalizeDegrees(Math.toDegrees(Math.atan2(yg, xg)))
        val lat = Math.toDegrees(Math.atan2(zg, sqrt(xg * xg + yg * yg)))
        val dist = sqrt(xg * xg + yg * yg + zg * zg)
        return EclCoord(lon, lat, dist)
    }
}
