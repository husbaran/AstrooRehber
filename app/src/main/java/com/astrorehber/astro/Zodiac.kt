package com.astrorehber.astro

enum class Element { ATES, TOPRAK, HAVA, SU }
enum class Modality(val turkishName: String) {
    ONCU("öncü"), SABIT("sabit"), DEGISKEN("değişken")
}

enum class ZodiacSign(
    val turkishName: String,
    val glyph: String,
    val element: Element,
    val modality: Modality,
    val ruler: Planet
) {
    KOC("Koç", "♈", Element.ATES, Modality.ONCU, Planet.MARS),
    BOGA("Boğa", "♉", Element.TOPRAK, Modality.SABIT, Planet.VENUS),
    IKIZLER("İkizler", "♊", Element.HAVA, Modality.DEGISKEN, Planet.MERKUR),
    YENGEC("Yengeç", "♋", Element.SU, Modality.ONCU, Planet.AY),
    ASLAN("Aslan", "♌", Element.ATES, Modality.SABIT, Planet.GUNES),
    BASAK("Başak", "♍", Element.TOPRAK, Modality.DEGISKEN, Planet.MERKUR),
    TERAZI("Terazi", "♎", Element.HAVA, Modality.ONCU, Planet.VENUS),
    AKREP("Akrep", "♏", Element.SU, Modality.SABIT, Planet.PLUTO),
    YAY("Yay", "♐", Element.ATES, Modality.DEGISKEN, Planet.JUPITER),
    OGLAK("Oğlak", "♑", Element.TOPRAK, Modality.ONCU, Planet.SATURN),
    KOVA("Kova", "♒", Element.HAVA, Modality.SABIT, Planet.URANUS),
    BALIK("Balık", "♓", Element.SU, Modality.DEGISKEN, Planet.NEPTUN);

    companion object {
        /** Returns sign from ecliptic longitude (0-360°). */
        fun fromLongitude(longitudeDeg: Double): ZodiacSign {
            val idx = (normalizeDegrees(longitudeDeg) / 30.0).toInt().coerceIn(0, 11)
            return entries[idx]
        }

        /** Degree within sign (0-30°). */
        fun degreeInSign(longitudeDeg: Double): Double {
            return normalizeDegrees(longitudeDeg) % 30.0
        }

        /** Format like "12° ♌ 34'" */
        fun formatLongitude(longitudeDeg: Double): String {
            val sign = fromLongitude(longitudeDeg)
            val deg = degreeInSign(longitudeDeg)
            val whole = deg.toInt()
            val minutes = ((deg - whole) * 60).toInt()
            return "$whole° ${sign.glyph} ${"%02d".format(minutes)}'"
        }
    }
}
