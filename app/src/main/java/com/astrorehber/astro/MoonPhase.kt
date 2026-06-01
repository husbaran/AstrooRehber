package com.astrorehber.astro

enum class MoonPhaseKind(val turkishName: String, val emoji: String) {
    YENI_AY("Yeni Ay", "🌑"),
    HILAL_BUYUYEN("Büyüyen Hilal", "🌒"),
    ILK_DORDUN("İlk Dördün", "🌓"),
    BUYUYEN_DOLUNAY("Şişkin Ay (Büyüyen)", "🌔"),
    DOLUNAY("Dolunay", "🌕"),
    KUCULEN_DOLUNAY("Şişkin Ay (Küçülen)", "🌖"),
    SON_DORDUN("Son Dördün", "🌗"),
    HILAL_KUCULEN("Küçülen Hilal", "🌘")
}

data class MoonPhase(
    val kind: MoonPhaseKind,
    val illuminationPercent: Int,
    val ageDays: Double
)

object MoonPhaseEngine {

    /** Computes the moon phase given current Sun & Moon longitudes. */
    fun compute(sunLongitude: Double, moonLongitude: Double): MoonPhase {
        var phaseAngle = normalizeDegrees(moonLongitude - sunLongitude)
        val ageDays = phaseAngle / 360.0 * 29.5305882
        val illum = ((1 - Math.cos(phaseAngle.deg())) / 2 * 100).toInt().coerceIn(0, 100)
        val kind = when {
            phaseAngle < 22.5 || phaseAngle >= 337.5 -> MoonPhaseKind.YENI_AY
            phaseAngle < 67.5 -> MoonPhaseKind.HILAL_BUYUYEN
            phaseAngle < 112.5 -> MoonPhaseKind.ILK_DORDUN
            phaseAngle < 157.5 -> MoonPhaseKind.BUYUYEN_DOLUNAY
            phaseAngle < 202.5 -> MoonPhaseKind.DOLUNAY
            phaseAngle < 247.5 -> MoonPhaseKind.KUCULEN_DOLUNAY
            phaseAngle < 292.5 -> MoonPhaseKind.SON_DORDUN
            else -> MoonPhaseKind.HILAL_KUCULEN
        }
        return MoonPhase(kind, illum, ageDays)
    }
}
