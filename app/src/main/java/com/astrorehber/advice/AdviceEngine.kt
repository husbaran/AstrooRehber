package com.astrorehber.advice

import com.astrorehber.astro.MoonPhaseEngine
import com.astrorehber.astro.Planet
import com.astrorehber.astro.PlanetEngine
import com.astrorehber.astro.ZodiacSign
import com.astrorehber.astro.julianDayUt
import com.astrorehber.data.UserProfile
import kotlinx.datetime.LocalDate
import kotlin.random.Random

/**
 * Deterministically produces a daily reading from the user's manually entered
 * signs plus today's transiting Moon position (lightweight, no birth chart).
 */
object AdviceEngine {

    fun adviceFor(profile: UserProfile, date: LocalDate): DailyAdvice {
        val jd = julianDayUt(date.year, date.monthNumber, date.dayOfMonth, 12, 0)
        val transitSun = PlanetEngine.position(Planet.GUNES, jd)
        val transitMoon = PlanetEngine.position(Planet.AY, jd)
        val moonPhase = MoonPhaseEngine.compute(transitSun.longitude, transitMoon.longitude)

        val userElement = profile.sunSign.element
        val transitMoonSign = transitMoon.sign
        val transitElement = transitMoonSign.element
        val ascElement = profile.ascendantSign.element

        val seed = profile.signature() xor date.toEpochDays().toLong()
        val rng = Random(seed)

        val headline = pick(rng, AdvicePools.headlinesByPhase.getValue(moonPhase.kind))
        val bodyPool = AdvicePools.bodyByElementPair[userElement to transitElement]
            ?: AdvicePools.bodyByElementPair.getValue(userElement to userElement)
        val body = pick(rng, bodyPool)
        val action = pick(rng, AdvicePools.actionsByAscElement.getValue(ascElement))

        val mood = pick(rng, AdvicePools.moods)
        val color = pick(rng, AdvicePools.luckyColors)
        val lucky = rng.nextInt(99) + 1

        val personalizedHeadline = personalize(headline, profile.sunSign, transitMoonSign)

        return DailyAdvice(
            headline = personalizedHeadline,
            body = body,
            action = action,
            mood = mood,
            luckyColor = color,
            luckyNumber = lucky,
            moonPhase = moonPhase.kind,
            transitMoonSign = transitMoonSign
        )
    }

    private fun <T> pick(rng: Random, list: List<T>): T = list[rng.nextInt(list.size)]

    private fun personalize(headline: String, sun: ZodiacSign, transitMoon: ZodiacSign): String {
        return if (sun == transitMoon) {
            "$headline — bugün özellikle senin için, ${sun.turkishName} ${sun.glyph}"
        } else {
            "$headline — ${sun.turkishName} ${sun.glyph} için, Ay ${transitMoon.turkishName}'da ${transitMoon.glyph}"
        }
    }
}
