package com.astrorehber.events

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

/**
 * Hand-curated catalog of major astrological events visible / relevant from
 * Turkey for 2026 and 2027. Dates from publicly known ephemerides
 * (NASA eclipse pages, in-the-sky.org, time-and-date). Times rounded to date
 * in Turkey local time. Each event also carries human-friendly advice.
 */
object EventsCatalog {

    val events: List<AstrologicalEvent> = listOf(
        // 2026
        AstrologicalEvent(
            date = LocalDate(2026, 1, 3),
            type = EventType.DOLUNAY,
            title = "Yengeç Dolunayı",
            description = "Duygusal berraklık, aile ve ev temaları öne çıkar.",
            zodiacGlyph = "♋",
            advice = "Sevdiklerinle vakit geçir. Eski bir defteri sıcakkanlı bir konuşmayla kapat."
        ),
        AstrologicalEvent(
            date = LocalDate(2026, 1, 19),
            type = EventType.YENI_AY,
            title = "Oğlak Yeni Ayı",
            description = "Hedef koymak, disiplinli niyet için elverişli.",
            zodiacGlyph = "♑",
            advice = "Yılın ilk somut planını yaz. Üç ay sonrası için ölçülebilir bir hedef belirle."
        ),
        AstrologicalEvent(
            date = LocalDate(2026, 2, 1),
            type = EventType.DOLUNAY,
            title = "Aslan Dolunayı",
            description = "Bireysel ifade, sahnenin ortasına çıkma vakti.",
            zodiacGlyph = "♌",
            advice = "Görünür ol. Bir yaratıcı işini paylaşmaktan çekinme."
        ),
        AstrologicalEvent(
            date = LocalDate(2026, 2, 17),
            type = EventType.GUNES_TUTULMASI,
            title = "Halkalı Güneş Tutulması",
            description = "Kova burcunda halkalı tutulma. Topluluk ve gelecek vizyonu yeniden ayarlanır.",
            zodiacGlyph = "♒",
            advice = "Büyük kararları erteleme; ama acele de etme. 3 günde 3 sayfa not tut."
        ),
        AstrologicalEvent(
            date = LocalDate(2026, 3, 3),
            type = EventType.AY_TUTULMASI,
            title = "Tam Ay Tutulması",
            description = "Başak burcunda tam Ay tutulması. Sağlık ve rutinler yüzeye çıkar.",
            zodiacGlyph = "♍",
            advice = "Bedeninin sesini dinle. Tek bir küçük alışkanlığı sadeleştir."
        ),
        AstrologicalEvent(
            date = LocalDate(2026, 3, 20),
            type = EventType.EKINOKS,
            title = "İlkbahar Ekinoksu",
            description = "Güneş Koç'a girer; astrolojik yeni yıl başlar.",
            zodiacGlyph = "♈",
            advice = "Yeni başlangıçlar için yıldız zamanı. Bir tohum at — bir niyet, bir proje, bir alışkanlık."
        ),
        AstrologicalEvent(
            date = LocalDate(2026, 3, 18),
            type = EventType.YENI_AY,
            title = "Balık Yeni Ayı",
            description = "Sezgisel niyetler için yumuşak bir kapı.",
            zodiacGlyph = "♓",
            advice = "Bu gece içe dön. Rüyalarını sabah yazmak için kenara bir defter koy."
        ),
        AstrologicalEvent(
            date = LocalDate(2026, 5, 1),
            type = EventType.DOLUNAY,
            title = "Akrep Dolunayı",
            description = "Yoğun bir farkındalık dalgası. Saklı olan görünür hale gelir.",
            zodiacGlyph = "♏",
            advice = "Kendinden sakladığın bir gerçeği sayfaya dök. Kimseye göstermesen de olur."
        ),
        AstrologicalEvent(
            date = LocalDate(2026, 5, 11),
            type = EventType.RETRO_BASLANGIC,
            title = "Merkür Retrosu Başlar",
            description = "Merkür İkizler'de geri harekete geçer. İletişim ve teknolojide yavaşlamalar.",
            advice = "Önemli sözleşmeleri 3 hafta sonraya bırakabilirsen bırak. Eski projelere dön."
        ),
        AstrologicalEvent(
            date = LocalDate(2026, 6, 3),
            type = EventType.RETRO_BITIS,
            title = "Merkür Düz Hareket",
            description = "Merkür direkte. İletişim akışı geri döner.",
            advice = "Beklettiğin mesajı şimdi gönder. Belgeleri imzalamak için müsait bir hafta."
        ),
        AstrologicalEvent(
            date = LocalDate(2026, 6, 21),
            type = EventType.GUN_DONUMU,
            title = "Yaz Gün Dönümü",
            description = "Yılın en uzun günü; Güneş Yengeç'e geçer.",
            zodiacGlyph = "♋",
            advice = "Güneş doğarken birkaç dakika dışarıda dur. Ne istediğini hisset, sonra yaz."
        ),
        AstrologicalEvent(
            date = LocalDate(2026, 7, 29),
            type = EventType.DOLUNAY,
            title = "Kova Dolunayı",
            description = "Bağımsızlık ve gelecek vizyonu öne çıkar.",
            zodiacGlyph = "♒",
            advice = "Kimsenin görmediği bir fikrini bir tanıdığınla paylaş."
        ),
        AstrologicalEvent(
            date = LocalDate(2026, 8, 12),
            type = EventType.DOLUNAY,
            title = "Süper Dolunay (Kova)",
            description = "Yıla damga vuran ikinci Kova dolunayı, çok parlak görünür.",
            zodiacGlyph = "♒",
            advice = "Geceyi açık havada geçir. Üç dilek dile, birini somut bir adıma çevir."
        ),
        AstrologicalEvent(
            date = LocalDate(2026, 8, 22),
            type = EventType.GUNES_TUTULMASI,
            title = "Tam Güneş Tutulması",
            description = "Aslan-Başak eşiğinde tam Güneş tutulması. İspanya ve İzlanda'da total.",
            zodiacGlyph = "♌",
            advice = "Hayatında 'bana ne kalıyor?' sorusunu sor. Önümüzdeki 6 ay için temel bir niyet seç."
        ),
        AstrologicalEvent(
            date = LocalDate(2026, 9, 7),
            type = EventType.AY_TUTULMASI,
            title = "Tam Ay Tutulması",
            description = "Balık burcunda tam Ay tutulması. Türkiye'den görünür.",
            zodiacGlyph = "♓",
            advice = "Bırakman gereken eski bir öyküyü yaz, sonra dosyayı kapat ve uyu."
        ),
        AstrologicalEvent(
            date = LocalDate(2026, 9, 23),
            type = EventType.EKINOKS,
            title = "Sonbahar Ekinoksu",
            description = "Güneş Terazi'ye geçer; denge ve ilişkiler vakti.",
            zodiacGlyph = "♎",
            advice = "Tek bir ilişkide neyi adil bulmadığını dürüstçe ifade et."
        ),
        AstrologicalEvent(
            date = LocalDate(2026, 10, 26),
            type = EventType.DOLUNAY,
            title = "Boğa Dolunayı",
            description = "Beden, para, değerler aydınlanır.",
            zodiacGlyph = "♉",
            advice = "Cüzdanını ve harcamalarını sade bir tabloya geçir. Bir keyif harcaması da yaz."
        ),
        AstrologicalEvent(
            date = LocalDate(2026, 11, 9),
            type = EventType.RETRO_BASLANGIC,
            title = "Merkür Retrosu Başlar",
            description = "Akrep–Terazi geçişinde retro. Geçmiş ilişkiler gündeme gelebilir.",
            advice = "Eski bir mesajı yeniden okumak için sakin bir saatte buluş kendinle."
        ),
        AstrologicalEvent(
            date = LocalDate(2026, 12, 4),
            type = EventType.DOLUNAY,
            title = "İkizler Dolunayı",
            description = "Fikirler, kitaplar, kısa yolculuklar.",
            zodiacGlyph = "♊",
            advice = "Yarım kalan bir okumayı bitir. 5 satırlık bir özetini yaz."
        ),
        AstrologicalEvent(
            date = LocalDate(2026, 12, 21),
            type = EventType.GUN_DONUMU,
            title = "Kış Gün Dönümü",
            description = "Güneş Oğlak'a geçer; gözden geçirme zamanı.",
            zodiacGlyph = "♑",
            advice = "Bu yıl ne öğrendin? Üç maddelik bir öz değerlendirme yeterli."
        ),

        // 2027
        AstrologicalEvent(
            date = LocalDate(2027, 1, 22),
            type = EventType.DOLUNAY,
            title = "Aslan Dolunayı",
            description = "Yaratıcı ifade ve çocuksu sevinç çağrısı.",
            zodiacGlyph = "♌",
            advice = "Çocukken sevdiğin bir aktiviteyi 20 dakika yap."
        ),
        AstrologicalEvent(
            date = LocalDate(2027, 2, 6),
            type = EventType.GUNES_TUTULMASI,
            title = "Halkalı Güneş Tutulması",
            description = "Kova–Balık eşiğinde halkalı tutulma.",
            zodiacGlyph = "♒",
            advice = "Topluluk içinde olduğun bir rolü gözden geçir. Devam mı, dönüşüm mü?"
        ),
        AstrologicalEvent(
            date = LocalDate(2027, 2, 20),
            type = EventType.AY_TUTULMASI,
            title = "Yarı Gölgeli Ay Tutulması",
            description = "Başak'ta yumuşak ama anlamlı bir tutulma.",
            zodiacGlyph = "♍",
            advice = "Bir küçük temizlik. Bir çekmece, bir telefon klasörü, bir alışkanlık."
        ),
        AstrologicalEvent(
            date = LocalDate(2027, 3, 20),
            type = EventType.EKINOKS,
            title = "İlkbahar Ekinoksu",
            description = "Güneş Koç'a girer.",
            zodiacGlyph = "♈",
            advice = "Bu yıl seni heyecanlandıran bir riski adlandır. İlk küçük adımı 7 gün içinde at."
        ),
        AstrologicalEvent(
            date = LocalDate(2027, 8, 2),
            type = EventType.GUNES_TUTULMASI,
            title = "Tam Güneş Tutulması",
            description = "Aslan'da 6 dakikalık tam Güneş tutulması — yüzyılın en uzun tutulmalarından, güney Türkiye'de doruk fazda görünür.",
            zodiacGlyph = "♌",
            advice = "Bu astrolojik dönemin kapısı geniş açılır. Bir niyet yaz, 6 ay tekrar oku."
        ),
        AstrologicalEvent(
            date = LocalDate(2027, 8, 17),
            type = EventType.AY_TUTULMASI,
            title = "Kova Ay Tutulması",
            description = "Kollektif temaların altı tekrar çizilir.",
            zodiacGlyph = "♒",
            advice = "Topluluk içinde bir konuda sessiz kaldığın yer neresi? Onu nazikçe yaz."
        ),
        AstrologicalEvent(
            date = LocalDate(2027, 9, 23),
            type = EventType.EKINOKS,
            title = "Sonbahar Ekinoksu",
            description = "Güneş Terazi'ye geçer.",
            zodiacGlyph = "♎",
            advice = "Mevsimi sade bir akşam yemeğiyle karşıla. Bir kişiye teşekkür et."
        ),
        AstrologicalEvent(
            date = LocalDate(2027, 12, 22),
            type = EventType.GUN_DONUMU,
            title = "Kış Gün Dönümü",
            description = "Yılı kapatma zamanı.",
            zodiacGlyph = "♑",
            advice = "Önce nefes. Sonra üç şükür. Sonra üç hedef. Sonra uyku."
        )
    )

    /** Returns the next upcoming event from today (inclusive). */
    fun nextUpcoming(today: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault())): AstrologicalEvent? =
        events.firstOrNull { it.date >= today }

    fun upcomingFrom(today: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault())): List<AstrologicalEvent> =
        events.filter { it.date >= today }
}
