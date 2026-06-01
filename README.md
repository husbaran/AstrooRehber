# AstroRehber

> Yıldızların altında günlük rehberin.

AstroRehber, kullanıcının doğum tarihi, saati ve yerine göre **gerçek astronomik hesaplamalarla** doğum haritasını çıkartan, her gün kişiye özel kısa bir astroloji tavsiyesi sunan ve yaklaşan astrolojik olayları (dolunay, yeni ay, tutulma, retro vb.) bildiren bir Android uygulamasıdır.

Tamamen **offline** çalışır — hiçbir API'ye bağımlı değildir.

## Özellikler

- **Manuel burç girişi**: Güneş, Ay, Yükselen ve Venüs burcunu bir kez seç — uygulama hatırlar.
- **Günlük Kişisel Tavsiye**: Burçlarına ve günün Ay geçişine göre her gün farklı, kısa bir öğüt.
- **Astrolojik Olay Takvimi**: 2026–2027 dolunay, yeni ay, tutulma, ekinoks tarihleri ve tavsiyeleri.
- **Bir Kez Veri Girişi**: Bilgiler `DataStore` ile cihazda saklanır.

## Çalıştırma

### Gereksinimler

- Android Studio Iguana (2023.2) veya daha yeni
- JDK 17
- Android SDK 34
- minSdk 26 (Android 8.0+)

### Adımlar

1. Klasörü Android Studio'da aç: **File → Open** → `ASTROREHBER` klasörünü seç.
2. Gradle sync tamamlanana kadar bekle.
3. Bir cihaz veya emülatör seç (API 26+).
4. **Run 'app'** (Shift + F10).

## Mimari

```
app/src/main/java/com/astrorehber/
├── astro/         # Astronomik motor (Schlyter/Meeus)
│   ├── AstroMath.kt          # Julian Day, Kepler çözümü, koordinat dönüşümleri
│   ├── PlanetEngine.kt       # 10 gezegenin geocentric pozisyonları
│   ├── HouseEngine.kt        # Ascendant, MC, Whole-Sign evler
│   ├── AspectEngine.kt       # Kavuşum, üçgen, kare, sekstil vb.
│   ├── MoonPhase.kt          # Ay fazı hesaplama
│   ├── NatalChart.kt         # Hepsini birleştiren fasad
│   ├── Planet.kt, Zodiac.kt  # Veri modelleri
├── advice/        # Günlük tavsiye motoru
│   ├── AdvicePools.kt        # Türkçe metin havuzları
│   └── AdviceEngine.kt       # Deterministik (chart + tarih) seçim
├── events/        # Astrolojik olay kataloğu
├── data/          # BirthData, City, DataStore, offline şehir DB
└── ui/            # Jetpack Compose ekranları
    ├── theme/                # Tema, renk, tipografi
    ├── onboarding/           # İlk açılışta veri girişi
    ├── home/                 # Ana ekran (günlük + olay)
    ├── chart/                # Doğum haritası çemberi
    └── events/               # Olaylar listesi
```

## Astronomik Hassasiyet

Bu uygulama Paul Schlyter'ın temel yörünge öğeleri (Keplerian) tabanlı algoritmasını kullanır:

- Güneş: ~ 1 yay-dakika
- Ay: ~ 2-5 yay-dakika (büyük pertürbasyonlar dahil)
- İç gezegenler (Merkür–Mars): birkaç yay-dakika
- Dış gezegenler (Jüpiter–Pluto): ~ 0.5°

Günlük rehber ve doğum haritası gösterimi için fazlasıyla yeterlidir. Profesyonel astrolojik araştırma için Swiss Ephemeris entegrasyonu eklenebilir.

## Yol Haritası

- [ ] Bildirim: günlük tavsiye için günde bir push.
- [ ] Birden çok profil (aile üyeleri).
- [ ] Swiss Ephemeris opsiyonel modülü.
- [ ] Olay kataloğunu otomatik üretmek (bir sonraki dolunayı dinamik hesaplama).
- [ ] Transit tarayıcı: bugünkü gezegen geçişlerinin haritana etkisi.

## Lisans

MIT
