package com.astrorehber.advice

import com.astrorehber.astro.Element
import com.astrorehber.astro.MoonPhaseKind

/**
 * Curated Turkish text fragments. The advice engine picks one fragment from
 * each pool deterministically (date + chart seed) and assembles a unique
 * short reading.
 */
object AdvicePools {

    val headlinesByPhase: Map<MoonPhaseKind, List<String>> = mapOf(
        MoonPhaseKind.YENI_AY to listOf(
            "Yeni bir tohum atmak için elverişli bir gün",
            "Niyet kurmaya açık bir başlangıç",
            "Sessiz bir başlangıcın eşiğindesin"
        ),
        MoonPhaseKind.HILAL_BUYUYEN to listOf(
            "Küçük ilk adımlar büyüyor",
            "Cesaretin yavaşça birikiyor",
            "Bugün ufak ama net bir hamle ister"
        ),
        MoonPhaseKind.ILK_DORDUN to listOf(
            "Direnci dönüştürmenin günü",
            "Karar verme zamanı yaklaşıyor",
            "Yarımkalan bir konuyu kıvılcımlamak için iyi gün"
        ),
        MoonPhaseKind.BUYUYEN_DOLUNAY to listOf(
            "Olgunlaşmak üzere olan bir şey var",
            "Görünür hale gelmenin eşiğindesin",
            "Eylemleri ince ayarla; sonuca yaklaşıyorsun"
        ),
        MoonPhaseKind.DOLUNAY to listOf(
            "Dolunay altında bir farkındalık dalgası",
            "Görülmek ve görmek için açık bir gün",
            "Duygular yüzeyde; nazikçe karşıla"
        ),
        MoonPhaseKind.KUCULEN_DOLUNAY to listOf(
            "Hasadı toplama günü",
            "Kazanılanı sindirme zamanı",
            "Şükretmek için iyi bir an"
        ),
        MoonPhaseKind.SON_DORDUN to listOf(
            "Bırakman gerekenleri görmenin günü",
            "Bir kapıyı yumuşakça kapatmak için sakin gün",
            "Sadeleşmek bugün iyi gelecek"
        ),
        MoonPhaseKind.HILAL_KUCULEN to listOf(
            "Dinlenmenin de bir iş olduğu gün",
            "İçeri dönmenin huzurlu eşiği",
            "Sessizlikten ders alacaksın"
        )
    )

    /**
     * Body fragments. Indexed by user's sun-sign element ↔ transiting moon-sign element pair.
     * "Self" element means user and current moon are in similar energy.
     */
    val bodyByElementPair: Map<Pair<Element, Element>, List<String>> = mapOf(
        // ATEŞ — kullanıcı
        (Element.ATES to Element.ATES) to listOf(
            "Enerjin yüksek; girişkenliğini gerçek bir hedefe yönlendirirsen verimli olursun.",
            "İçindeki kıvılcımı boğmadan, ama yakıp da yorulmadan ilerle.",
            "Bugün cesaretin işine yarayacak; ama hız tuzağına dikkat et."
        ),
        (Element.ATES to Element.TOPRAK) to listOf(
            "Heyecanını somut, ölçülebilir bir adıma indir.",
            "Hızını bir an yavaşlatıp yapıyı kontrol et; küçük detay büyük fark açar.",
            "Sabırlı bir hamle, parlak bir hızdan daha çok kazandırır bugün."
        ),
        (Element.ATES to Element.HAVA) to listOf(
            "Fikirlerin uçuşuyor; en parlağını seç ve birine anlat.",
            "Sözle ateşi yakmak için iyi bir gün; konuşmaktan çekinme.",
            "Yaratıcı bir diyalog seni bir adım öteye taşıyabilir."
        ),
        (Element.ATES to Element.SU) to listOf(
            "Hızla hissi dengelemen lazım; iç sesini dinle.",
            "Tepki vermeden önce nefes al; bugün duygu yakıt değil, pusula.",
            "Bir an yavaşla, kalbini sor."
        ),

        // TOPRAK — kullanıcı
        (Element.TOPRAK to Element.ATES) to listOf(
            "Pratiğinin üzerine biraz cesaret koy; bir risk seni hızlandırır.",
            "Konforunu bozmadan yeni bir şey denemenin günü.",
            "Plana sadık kal ama enerjini biraz yükselt."
        ),
        (Element.TOPRAK to Element.TOPRAK) to listOf(
            "İstikrar enerjisi yüksek; yarım kalan bir işi bitirmek için ideal gün.",
            "Sade hareketler bugün kazandırır; gösterişten kaçın.",
            "Beden, para, mekân — temellerle uğraş."
        ),
        (Element.TOPRAK to Element.HAVA) to listOf(
            "Düşüncelerini başka bir gözle gözden geçir; tek bir varsayımı sorgula.",
            "Plana fikir karıştırmanın iyi olacağı gün.",
            "Bir sohbet sana yeni bir açı verecek."
        ),
        (Element.TOPRAK to Element.SU) to listOf(
            "Pratik aklınla duygularını barıştır.",
            "Bedeninin ihtiyacını duy; uyku, su, yumuşak yemek.",
            "Sevdiğin biriyle sakin bir dakika kur."
        ),

        // HAVA — kullanıcı
        (Element.HAVA to Element.ATES) to listOf(
            "Fikrini uygulamaya geçirmek için iyi bir motivasyon var.",
            "Bir konuşmayı eyleme dönüştür.",
            "Cesur bir mesaj göndermek için uygun bir gün."
        ),
        (Element.HAVA to Element.TOPRAK) to listOf(
            "Bir fikrini somutlaştırmak için sakin bir saat ayır.",
            "Zihnindeki bulutu liste haline getir.",
            "Çok şeyi düşünmek yerine bir tanesini tamamla."
        ),
        (Element.HAVA to Element.HAVA) to listOf(
            "Zihin berrak; iletişim hafif. Kıymetli birine ulaş.",
            "Bir konsepti basitleştirip anlatmak için iyi gün.",
            "Okumak, yazmak, sormak — hepsi akacak."
        ),
        (Element.HAVA to Element.SU) to listOf(
            "Düşüncelerinin altındaki duyguyu fark et.",
            "Mantıkla değil sezgiyle alacağın bir karar var.",
            "Bir başkasının duygusuna kulak ver, çözmeye değil."
        ),

        // SU — kullanıcı
        (Element.SU to Element.ATES) to listOf(
            "Duygunun içindeki cesareti çıkar; harekete dök.",
            "Kalbinden geçeni söylemek için iyi gün.",
            "Bir tepkiyi eyleme çevirmenin sırası."
        ),
        (Element.SU to Element.TOPRAK) to listOf(
            "Duyguya bir form ver; yaz, çiz, pişir.",
            "Bedenle temas etmenin iyi geleceği gün.",
            "Bir alanı sevgiyle düzenlemek seni rahatlatacak."
        ),
        (Element.SU to Element.HAVA) to listOf(
            "Hissettiğini dile dökmenin sırası.",
            "Bir konuşma duyguyu yatıştırabilir.",
            "Sözcüklerinle kalbini selamla."
        ),
        (Element.SU to Element.SU) to listOf(
            "Derinlerde bir akış var; bugün koru kendini.",
            "Sezgine güvenebileceğin bir gün.",
            "Yumuşaklık güçtür; bugün hatırla."
        )
    )

    /**
     * Action prompts (very short). Indexed by user's ascendant element.
     * If ascendant is unknown we fall back to sun sign element.
     */
    val actionsByAscElement: Map<Element, List<String>> = mapOf(
        Element.ATES to listOf(
            "Bugün on dakikalık tek bir hareket et: yürüyüş, dans, esneme.",
            "Bir cesur mesajı 'gönder'e bas.",
            "Vücudunu önce ısıt, sonra karar al.",
            "Görmek istediğin bir kişiye bugün ulaş."
        ),
        Element.TOPRAK to listOf(
            "Çekmeceni veya masanı 5 dakika düzenle.",
            "Bir bardak su iç ve telefonu 20 dakika kapalı bırak.",
            "Bir liste yap: bugün üç madde, yarın iki madde.",
            "Bir yemeği yavaşça, telefonsuz ye."
        ),
        Element.HAVA to listOf(
            "Bir sayfa yaz, kimseye gösterme.",
            "Bir kitabı 10 dakika oku.",
            "Bir dosta sesli mesaj gönder.",
            "Bir varsayımını sorgula: 'gerçekten öyle mi?'"
        ),
        Element.SU to listOf(
            "Uzun ve sıcak bir duş al.",
            "Sevdiğin bir şarkıyı baştan sona dinle.",
            "Üç şükür yaz, sesli oku.",
            "Bir küçük çocukla, hayvanla ya da bitkiyle vakit geçir."
        )
    )

    val moods: List<String> = listOf(
        "Sakin", "Açık", "Berrak", "Dingin", "Cesaretli", "Şefkatli",
        "Odaklı", "Yaratıcı", "Sezgisel", "Disiplinli", "Esnek", "Yumuşak"
    )

    val luckyColors: List<String> = listOf(
        "Lacivert", "Altın", "Gümüş", "Mor", "Turkuaz", "Bordo",
        "Yeşil", "Beyaz", "Pembe", "Antrasit"
    )
}
