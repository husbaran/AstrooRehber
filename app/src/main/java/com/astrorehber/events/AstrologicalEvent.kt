package com.astrorehber.events

import kotlinx.datetime.LocalDate

enum class EventType(val turkishName: String, val emoji: String) {
    YENI_AY("Yeni Ay", "🌑"),
    DOLUNAY("Dolunay", "🌕"),
    GUNES_TUTULMASI("Güneş Tutulması", "🌒"),
    AY_TUTULMASI("Ay Tutulması", "🌖"),
    RETRO_BASLANGIC("Retro Başlangıcı", "↺"),
    RETRO_BITIS("Retro Bitişi", "↻"),
    EKINOKS("Ekinoks", "✦"),
    GUN_DONUMU("Gün Dönümü", "☀")
}

data class AstrologicalEvent(
    val date: LocalDate,
    val type: EventType,
    val title: String,
    val description: String,
    val zodiacGlyph: String? = null,
    val advice: String
) {
    val displayTitle: String
        get() = if (zodiacGlyph != null) "$title $zodiacGlyph" else title
}
