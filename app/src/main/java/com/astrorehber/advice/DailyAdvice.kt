package com.astrorehber.advice

import com.astrorehber.astro.MoonPhaseKind
import com.astrorehber.astro.ZodiacSign

data class DailyAdvice(
    val headline: String,
    val body: String,
    val action: String,
    val mood: String,
    val luckyColor: String,
    val luckyNumber: Int,
    val moonPhase: MoonPhaseKind,
    val transitMoonSign: ZodiacSign
)
