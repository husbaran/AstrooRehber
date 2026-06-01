package com.astrorehber.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.DeleteOutline
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Insights
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.NightsStay
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.astrorehber.advice.AdviceEngine
import com.astrorehber.advice.DailyAdvice
import com.astrorehber.data.UserProfile
import com.astrorehber.events.AstrologicalEvent
import com.astrorehber.events.EventsCatalog
import com.astrorehber.ui.components.GlassCard
import com.astrorehber.ui.components.KeyValueRow
import com.astrorehber.ui.components.SectionTitle
import com.astrorehber.ui.components.TagChip
import com.astrorehber.ui.theme.CelestialTeal
import com.astrorehber.ui.theme.MysticPurple
import com.astrorehber.ui.theme.NebulaPink
import com.astrorehber.ui.theme.StarGold
import com.astrorehber.ui.theme.StarGoldSoft
import com.astrorehber.ui.theme.TextPrimary
import com.astrorehber.ui.theme.TextSecondary
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

@Composable
fun HomeScreen(
    profile: UserProfile,
    onOpenChart: () -> Unit,
    onOpenEvents: () -> Unit,
    onEditProfile: () -> Unit,
    onResetProfile: () -> Unit
) {
    val today = remember { Clock.System.todayIn(TimeZone.currentSystemDefault()) }
    val advice = remember(profile, today) { AdviceEngine.adviceFor(profile, today) }
    val upcoming = remember(today) { EventsCatalog.nextUpcoming(today) }

    var menuOpen by remember { mutableStateOf(false) }
    var resetDialogOpen by remember { mutableStateOf(false) }

    val scroll = rememberScrollState()
    Column(
        Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars)
            .verticalScroll(scroll)
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            Box(Modifier.weight(1f)) {
                Header(name = profile.name, today = today, profile = profile)
            }
            Box {
                IconButton(onClick = { menuOpen = true }) {
                    Icon(
                        Icons.Outlined.MoreVert,
                        contentDescription = "Menü",
                        tint = TextPrimary
                    )
                }
                DropdownMenu(
                    expanded = menuOpen,
                    onDismissRequest = { menuOpen = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Profilimi Düzenle") },
                        leadingIcon = { Icon(Icons.Outlined.Edit, null) },
                        onClick = {
                            menuOpen = false
                            onEditProfile()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Profili Sıfırla") },
                        leadingIcon = { Icon(Icons.Outlined.DeleteOutline, null) },
                        onClick = {
                            menuOpen = false
                            resetDialogOpen = true
                        }
                    )
                }
            }
        }

        if (resetDialogOpen) {
            AlertDialog(
                onDismissRequest = { resetDialogOpen = false },
                title = { Text("Profili Sıfırla") },
                text = {
                    Text(
                        "Tüm astrolojik bilgilerin silinecek ve baştan giriş ekranına döneceksin. Devam edilsin mi?"
                    )
                },
                confirmButton = {
                    TextButton(onClick = {
                        resetDialogOpen = false
                        onResetProfile()
                    }) {
                        Text("Evet, sıfırla", color = MaterialTheme.colorScheme.error)
                    }
                },
                dismissButton = {
                    TextButton(onClick = { resetDialogOpen = false }) {
                        Text("Vazgeç")
                    }
                }
            )
        }

        Spacer(Modifier.height(20.dp))
        DailyAdviceCard(advice)
        Spacer(Modifier.height(16.dp))
        if (upcoming != null) UpcomingEventCard(upcoming, today)
        Spacer(Modifier.height(16.dp))
        QuickSnapshotCard(profile)
        Spacer(Modifier.height(20.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            FilledTonalButton(
                onClick = onOpenChart,
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(14.dp)
            ) {
                Icon(Icons.Outlined.Insights, null, modifier = Modifier.padding(end = 8.dp))
                Text("Profilim")
            }
            FilledTonalButton(
                onClick = onOpenEvents,
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(14.dp)
            ) {
                Icon(Icons.Outlined.CalendarMonth, null, modifier = Modifier.padding(end = 8.dp))
                Text("Olaylar")
            }
        }
        Spacer(Modifier.height(24.dp))
    }
}

@Composable
private fun Header(name: String, today: LocalDate, profile: UserProfile) {
    Column {
        Text("Merhaba ${name},".uppercase(), style = MaterialTheme.typography.labelLarge, color = StarGoldSoft)
        Spacer(Modifier.height(6.dp))
        Text(today.formatTurkish(), style = MaterialTheme.typography.headlineSmall, color = TextPrimary)
        Spacer(Modifier.height(10.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            TagChip("Güneş ${profile.sunSign.glyph} ${profile.sunSign.turkishName}")
            TagChip("Ay ${profile.moonSign.glyph} ${profile.moonSign.turkishName}")
            TagChip("Yükselen ${profile.ascendantSign.glyph}")
        }
    }
}

@Composable
private fun DailyAdviceCard(advice: DailyAdvice) {
    GlassCard(Modifier.fillMaxWidth()) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Outlined.AutoAwesome, null, tint = StarGold)
                Spacer(Modifier.size(8.dp))
                Text("GÜNÜN REHBERİ", style = MaterialTheme.typography.labelLarge, color = StarGoldSoft)
            }
            Spacer(Modifier.height(12.dp))
            Text(advice.headline, style = MaterialTheme.typography.headlineSmall, color = TextPrimary, fontWeight = FontWeight.Medium)
            Spacer(Modifier.height(12.dp))
            Text(advice.body, style = MaterialTheme.typography.bodyLarge, color = TextPrimary.copy(alpha = 0.9f))
            Spacer(Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Outlined.NightsStay, null, tint = MysticPurple)
                Spacer(Modifier.size(8.dp))
                Text("Bugün denemeye değer:", style = MaterialTheme.typography.labelMedium, color = MysticPurple)
            }
            Spacer(Modifier.height(6.dp))
            Text(advice.action, style = MaterialTheme.typography.bodyMedium, color = TextPrimary)
            Spacer(Modifier.height(16.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                TagChip(advice.moonPhase.emoji + " " + advice.moonPhase.turkishName)
                TagChip("Mod: ${advice.mood}")
                TagChip("Renk: ${advice.luckyColor}")
                TagChip("Sayı: ${advice.luckyNumber}")
            }
        }
    }
}

@Composable
private fun UpcomingEventCard(event: AstrologicalEvent, today: LocalDate) {
    val daysAway = event.date.toEpochDays() - today.toEpochDays()
    GlassCard(Modifier.fillMaxWidth()) {
        Column {
            SectionTitle("Yaklaşan Astrolojik Olay")
            Spacer(Modifier.height(14.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(event.type.emoji, style = MaterialTheme.typography.displaySmall)
                Spacer(Modifier.size(14.dp))
                Column {
                    Text(event.displayTitle, style = MaterialTheme.typography.titleLarge, color = TextPrimary, fontWeight = FontWeight.Medium)
                    val whenText = when (daysAway.toInt()) {
                        0 -> "Bugün"
                        1 -> "Yarın"
                        else -> "${daysAway} gün sonra · ${event.date.formatTurkishShort()}"
                    }
                    Text(whenText, style = MaterialTheme.typography.bodyMedium, color = CelestialTeal)
                }
            }
            Spacer(Modifier.height(14.dp))
            Text(event.description, style = MaterialTheme.typography.bodyMedium, color = TextPrimary.copy(alpha = 0.9f))
            Spacer(Modifier.height(12.dp))
            Text("Tavsiye", style = MaterialTheme.typography.labelMedium, color = NebulaPink)
            Spacer(Modifier.height(4.dp))
            Text(event.advice, style = MaterialTheme.typography.bodyMedium, color = TextPrimary)
        }
    }
}

@Composable
private fun QuickSnapshotCard(profile: UserProfile) {
    GlassCard(Modifier.fillMaxWidth()) {
        Column {
            SectionTitle("Astrolojik Profilin")
            Spacer(Modifier.height(12.dp))
            KeyValueRow("Güneş", "${profile.sunSign.glyph} ${profile.sunSign.turkishName}")
            KeyValueRow("Ay", "${profile.moonSign.glyph} ${profile.moonSign.turkishName}")
            KeyValueRow("Yükselen", "${profile.ascendantSign.glyph} ${profile.ascendantSign.turkishName}")
            KeyValueRow("Venüs", "${profile.venusSign.glyph} ${profile.venusSign.turkishName}")
        }
    }
}

private fun LocalDate.formatTurkish(): String {
    val months = arrayOf(
        "Ocak", "Şubat", "Mart", "Nisan", "Mayıs", "Haziran",
        "Temmuz", "Ağustos", "Eylül", "Ekim", "Kasım", "Aralık"
    )
    val days = arrayOf("Pazar", "Pazartesi", "Salı", "Çarşamba", "Perşembe", "Cuma", "Cumartesi")
    val dowIndex = (dayOfWeek.ordinal + 1) % 7
    return "${days[dowIndex]}, $dayOfMonth ${months[monthNumber - 1]} $year"
}

private fun LocalDate.formatTurkishShort(): String {
    val months = arrayOf("Oca", "Şub", "Mar", "Nis", "May", "Haz", "Tem", "Ağu", "Eyl", "Eki", "Kas", "Ara")
    return "$dayOfMonth ${months[monthNumber - 1]} $year"
}
