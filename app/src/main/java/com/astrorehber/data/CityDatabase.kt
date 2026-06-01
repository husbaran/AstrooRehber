package com.astrorehber.data

import java.text.Normalizer

/**
 * Offline geocoding store. Covers all 81 Turkish provinces plus a handful of
 * world capitals so users born abroad can still pick a city. Coordinates are
 * the official city-center values; tz is the IANA zone (handles DST history).
 */
object CityDatabase {

    val cities: List<City> = listOf(
        // ---- Türkiye (81 il) ----
        City("İstanbul", "Türkiye", 41.0082, 28.9784, "Europe/Istanbul"),
        City("Ankara", "Türkiye", 39.9334, 32.8597, "Europe/Istanbul"),
        City("İzmir", "Türkiye", 38.4192, 27.1287, "Europe/Istanbul"),
        City("Bursa", "Türkiye", 40.1956, 29.0610, "Europe/Istanbul"),
        City("Adana", "Türkiye", 37.0000, 35.3213, "Europe/Istanbul"),
        City("Gaziantep", "Türkiye", 37.0662, 37.3833, "Europe/Istanbul"),
        City("Konya", "Türkiye", 37.8746, 32.4932, "Europe/Istanbul"),
        City("Antalya", "Türkiye", 36.8841, 30.7056, "Europe/Istanbul"),
        City("Kayseri", "Türkiye", 38.7322, 35.4853, "Europe/Istanbul"),
        City("Mersin", "Türkiye", 36.8000, 34.6333, "Europe/Istanbul"),
        City("Eskişehir", "Türkiye", 39.7767, 30.5206, "Europe/Istanbul"),
        City("Diyarbakır", "Türkiye", 37.9144, 40.2306, "Europe/Istanbul"),
        City("Samsun", "Türkiye", 41.2867, 36.3300, "Europe/Istanbul"),
        City("Denizli", "Türkiye", 37.7765, 29.0864, "Europe/Istanbul"),
        City("Şanlıurfa", "Türkiye", 37.1591, 38.7969, "Europe/Istanbul"),
        City("Malatya", "Türkiye", 38.3552, 38.3095, "Europe/Istanbul"),
        City("Trabzon", "Türkiye", 41.0015, 39.7178, "Europe/Istanbul"),
        City("Erzurum", "Türkiye", 39.9000, 41.2700, "Europe/Istanbul"),
        City("Van", "Türkiye", 38.4942, 43.3800, "Europe/Istanbul"),
        City("Manisa", "Türkiye", 38.6191, 27.4289, "Europe/Istanbul"),
        City("Kahramanmaraş", "Türkiye", 37.5858, 36.9371, "Europe/Istanbul"),
        City("Sakarya", "Türkiye", 40.7569, 30.3781, "Europe/Istanbul"),
        City("Balıkesir", "Türkiye", 39.6484, 27.8826, "Europe/Istanbul"),
        City("Aydın", "Türkiye", 37.8444, 27.8458, "Europe/Istanbul"),
        City("Hatay", "Türkiye", 36.2025, 36.1606, "Europe/Istanbul"),
        City("Tekirdağ", "Türkiye", 40.9833, 27.5167, "Europe/Istanbul"),
        City("Muğla", "Türkiye", 37.2153, 28.3636, "Europe/Istanbul"),
        City("Afyonkarahisar", "Türkiye", 38.7507, 30.5567, "Europe/Istanbul"),
        City("Tokat", "Türkiye", 40.3167, 36.5500, "Europe/Istanbul"),
        City("Ordu", "Türkiye", 40.9839, 37.8764, "Europe/Istanbul"),
        City("Çorum", "Türkiye", 40.5506, 34.9556, "Europe/Istanbul"),
        City("Sivas", "Türkiye", 39.7477, 37.0179, "Europe/Istanbul"),
        City("Adıyaman", "Türkiye", 37.7648, 38.2786, "Europe/Istanbul"),
        City("Aksaray", "Türkiye", 38.3687, 34.0370, "Europe/Istanbul"),
        City("Amasya", "Türkiye", 40.6499, 35.8353, "Europe/Istanbul"),
        City("Ardahan", "Türkiye", 41.1105, 42.7022, "Europe/Istanbul"),
        City("Artvin", "Türkiye", 41.1828, 41.8183, "Europe/Istanbul"),
        City("Ağrı", "Türkiye", 39.7191, 43.0503, "Europe/Istanbul"),
        City("Bartın", "Türkiye", 41.6344, 32.3375, "Europe/Istanbul"),
        City("Batman", "Türkiye", 37.8812, 41.1320, "Europe/Istanbul"),
        City("Bayburt", "Türkiye", 40.2552, 40.2249, "Europe/Istanbul"),
        City("Bilecik", "Türkiye", 40.1426, 29.9793, "Europe/Istanbul"),
        City("Bingöl", "Türkiye", 38.8855, 40.4966, "Europe/Istanbul"),
        City("Bitlis", "Türkiye", 38.4011, 42.1078, "Europe/Istanbul"),
        City("Bolu", "Türkiye", 40.7392, 31.6089, "Europe/Istanbul"),
        City("Burdur", "Türkiye", 37.7212, 30.2900, "Europe/Istanbul"),
        City("Çanakkale", "Türkiye", 40.1553, 26.4142, "Europe/Istanbul"),
        City("Çankırı", "Türkiye", 40.6013, 33.6134, "Europe/Istanbul"),
        City("Düzce", "Türkiye", 40.8438, 31.1565, "Europe/Istanbul"),
        City("Edirne", "Türkiye", 41.6764, 26.5557, "Europe/Istanbul"),
        City("Elazığ", "Türkiye", 38.6810, 39.2264, "Europe/Istanbul"),
        City("Erzincan", "Türkiye", 39.7464, 39.4914, "Europe/Istanbul"),
        City("Giresun", "Türkiye", 40.9128, 38.3895, "Europe/Istanbul"),
        City("Gümüşhane", "Türkiye", 40.4604, 39.4807, "Europe/Istanbul"),
        City("Hakkari", "Türkiye", 37.5833, 43.7333, "Europe/Istanbul"),
        City("Iğdır", "Türkiye", 39.9237, 44.0450, "Europe/Istanbul"),
        City("Isparta", "Türkiye", 37.7648, 30.5566, "Europe/Istanbul"),
        City("Kars", "Türkiye", 40.6013, 43.0975, "Europe/Istanbul"),
        City("Kastamonu", "Türkiye", 41.3887, 33.7827, "Europe/Istanbul"),
        City("Kırıkkale", "Türkiye", 39.8468, 33.5153, "Europe/Istanbul"),
        City("Kırklareli", "Türkiye", 41.7333, 27.2167, "Europe/Istanbul"),
        City("Kırşehir", "Türkiye", 39.1425, 34.1709, "Europe/Istanbul"),
        City("Kilis", "Türkiye", 36.7184, 37.1212, "Europe/Istanbul"),
        City("Kocaeli", "Türkiye", 40.8533, 29.8815, "Europe/Istanbul"),
        City("Karabük", "Türkiye", 41.2061, 32.6204, "Europe/Istanbul"),
        City("Karaman", "Türkiye", 37.1759, 33.2287, "Europe/Istanbul"),
        City("Kütahya", "Türkiye", 39.4167, 29.9833, "Europe/Istanbul"),
        City("Mardin", "Türkiye", 37.3122, 40.7351, "Europe/Istanbul"),
        City("Muş", "Türkiye", 38.7333, 41.4912, "Europe/Istanbul"),
        City("Nevşehir", "Türkiye", 38.6939, 34.6857, "Europe/Istanbul"),
        City("Niğde", "Türkiye", 37.9667, 34.6833, "Europe/Istanbul"),
        City("Osmaniye", "Türkiye", 37.0742, 36.2469, "Europe/Istanbul"),
        City("Rize", "Türkiye", 41.0201, 40.5234, "Europe/Istanbul"),
        City("Siirt", "Türkiye", 37.9333, 41.9500, "Europe/Istanbul"),
        City("Sinop", "Türkiye", 42.0231, 35.1531, "Europe/Istanbul"),
        City("Şırnak", "Türkiye", 37.5164, 42.4611, "Europe/Istanbul"),
        City("Tunceli", "Türkiye", 39.0992, 39.5481, "Europe/Istanbul"),
        City("Uşak", "Türkiye", 38.6823, 29.4082, "Europe/Istanbul"),
        City("Yalova", "Türkiye", 40.6500, 29.2667, "Europe/Istanbul"),
        City("Yozgat", "Türkiye", 39.8181, 34.8147, "Europe/Istanbul"),
        City("Zonguldak", "Türkiye", 41.4564, 31.7987, "Europe/Istanbul"),

        // ---- Dünya ----
        City("Lefkoşa", "KKTC", 35.1856, 33.3823, "Asia/Nicosia"),
        City("Berlin", "Almanya", 52.5200, 13.4050, "Europe/Berlin"),
        City("Münih", "Almanya", 48.1351, 11.5820, "Europe/Berlin"),
        City("Frankfurt", "Almanya", 50.1109, 8.6821, "Europe/Berlin"),
        City("Hamburg", "Almanya", 53.5511, 9.9937, "Europe/Berlin"),
        City("Köln", "Almanya", 50.9375, 6.9603, "Europe/Berlin"),
        City("Viyana", "Avusturya", 48.2082, 16.3738, "Europe/Vienna"),
        City("Brüksel", "Belçika", 50.8503, 4.3517, "Europe/Brussels"),
        City("Paris", "Fransa", 48.8566, 2.3522, "Europe/Paris"),
        City("Londra", "İngiltere", 51.5074, -0.1278, "Europe/London"),
        City("Amsterdam", "Hollanda", 52.3676, 4.9041, "Europe/Amsterdam"),
        City("Roma", "İtalya", 41.9028, 12.4964, "Europe/Rome"),
        City("Milano", "İtalya", 45.4642, 9.1900, "Europe/Rome"),
        City("Madrid", "İspanya", 40.4168, -3.7038, "Europe/Madrid"),
        City("Barselona", "İspanya", 41.3851, 2.1734, "Europe/Madrid"),
        City("Atina", "Yunanistan", 37.9838, 23.7275, "Europe/Athens"),
        City("Sofya", "Bulgaristan", 42.6977, 23.3219, "Europe/Sofia"),
        City("Bükreş", "Romanya", 44.4268, 26.1025, "Europe/Bucharest"),
        City("Moskova", "Rusya", 55.7558, 37.6173, "Europe/Moscow"),
        City("Saint Petersburg", "Rusya", 59.9311, 30.3609, "Europe/Moscow"),
        City("Kiev", "Ukrayna", 50.4501, 30.5234, "Europe/Kiev"),
        City("Tahran", "İran", 35.6892, 51.3890, "Asia/Tehran"),
        City("Bakü", "Azerbaycan", 40.4093, 49.8671, "Asia/Baku"),
        City("Erivan", "Ermenistan", 40.1872, 44.5152, "Asia/Yerevan"),
        City("Tiflis", "Gürcistan", 41.7151, 44.8271, "Asia/Tbilisi"),
        City("Bağdat", "Irak", 33.3152, 44.3661, "Asia/Baghdad"),
        City("Şam", "Suriye", 33.5138, 36.2765, "Asia/Damascus"),
        City("Beyrut", "Lübnan", 33.8938, 35.5018, "Asia/Beirut"),
        City("Kudüs", "İsrail", 31.7683, 35.2137, "Asia/Jerusalem"),
        City("Riyad", "Suudi Arabistan", 24.7136, 46.6753, "Asia/Riyadh"),
        City("Dubai", "BAE", 25.2048, 55.2708, "Asia/Dubai"),
        City("Doha", "Katar", 25.2854, 51.5310, "Asia/Qatar"),
        City("Kahire", "Mısır", 30.0444, 31.2357, "Africa/Cairo"),
        City("Cezayir", "Cezayir", 36.7538, 3.0588, "Africa/Algiers"),
        City("Tunus", "Tunus", 36.8065, 10.1815, "Africa/Tunis"),
        City("Rabat", "Fas", 34.0209, -6.8417, "Africa/Casablanca"),
        City("Lagos", "Nijerya", 6.5244, 3.3792, "Africa/Lagos"),
        City("New York", "ABD", 40.7128, -74.0060, "America/New_York"),
        City("Los Angeles", "ABD", 34.0522, -118.2437, "America/Los_Angeles"),
        City("Chicago", "ABD", 41.8781, -87.6298, "America/Chicago"),
        City("Toronto", "Kanada", 43.6532, -79.3832, "America/Toronto"),
        City("Meksiko", "Meksika", 19.4326, -99.1332, "America/Mexico_City"),
        City("Buenos Aires", "Arjantin", -34.6037, -58.3816, "America/Argentina/Buenos_Aires"),
        City("Sao Paulo", "Brezilya", -23.5505, -46.6333, "America/Sao_Paulo"),
        City("Tokyo", "Japonya", 35.6762, 139.6503, "Asia/Tokyo"),
        City("Seul", "Güney Kore", 37.5665, 126.9780, "Asia/Seoul"),
        City("Pekin", "Çin", 39.9042, 116.4074, "Asia/Shanghai"),
        City("Şangay", "Çin", 31.2304, 121.4737, "Asia/Shanghai"),
        City("Yeni Delhi", "Hindistan", 28.6139, 77.2090, "Asia/Kolkata"),
        City("Bangkok", "Tayland", 13.7563, 100.5018, "Asia/Bangkok"),
        City("Sidney", "Avustralya", -33.8688, 151.2093, "Australia/Sydney"),
        City("Melbourne", "Avustralya", -37.8136, 144.9631, "Australia/Melbourne")
    )

    /** Diacritics-insensitive, case-insensitive prefix/contains search. */
    fun search(query: String, limit: Int = 20): List<City> {
        if (query.isBlank()) return cities.take(limit)
        val q = query.normalize()
        return cities
            .asSequence()
            .filter { it.name.normalize().contains(q) || it.country.normalize().contains(q) }
            .sortedWith(compareBy(
                { !it.name.normalize().startsWith(q) },
                { it.name }
            ))
            .take(limit)
            .toList()
    }

    private fun String.normalize(): String {
        val n = Normalizer.normalize(this, Normalizer.Form.NFD)
            .replace("\\p{Mn}+".toRegex(), "")
        return n
            .replace('ı', 'i').replace('İ', 'i')
            .replace('ş', 's').replace('Ş', 's')
            .replace('ğ', 'g').replace('Ğ', 'g')
            .replace('ç', 'c').replace('Ç', 'c')
            .replace('ö', 'o').replace('Ö', 'o')
            .replace('ü', 'u').replace('Ü', 'u')
            .lowercase()
    }
}
