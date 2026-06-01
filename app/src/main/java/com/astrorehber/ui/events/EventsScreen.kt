package com.astrorehber.ui.events

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.astrorehber.events.AstrologicalEvent
import com.astrorehber.events.EventsCatalog
import com.astrorehber.ui.components.GlassCard
import com.astrorehber.ui.components.TagChip
import com.astrorehber.ui.theme.CelestialTeal
import com.astrorehber.ui.theme.NebulaPink
import com.astrorehber.ui.theme.TextPrimary
import com.astrorehber.ui.theme.TextSecondary
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

@Composable
fun EventsScreen(onBack: () -> Unit) {
    val today = remember { Clock.System.todayIn(TimeZone.currentSystemDefault()) }
    val upcoming = remember(today) { EventsCatalog.upcomingFrom(today) }

    Column(
        Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.Outlined.ArrowBack, "Geri", tint = TextPrimary)
            }
            Text(
                "Yaklaşan Astrolojik Olaylar",
                style = MaterialTheme.typography.headlineSmall,
                color = TextPrimary,
                fontWeight = FontWeight.Medium
            )
        }

        LazyColumn(
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(upcoming) { event ->
                EventCard(event, today)
            }
            item { Spacer(Modifier.size(24.dp)) }
        }
    }
}

@Composable
private fun EventCard(event: AstrologicalEvent, today: LocalDate) {
    val daysAway = event.date.toEpochDays() - today.toEpochDays()
    GlassCard(Modifier.fillMaxWidth()) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(event.type.emoji, style = MaterialTheme.typography.headlineMedium)
                Spacer(Modifier.size(12.dp))
                Column(Modifier.weight(1f)) {
                    Text(
                        event.displayTitle,
                        style = MaterialTheme.typography.titleLarge,
                        color = TextPrimary,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        event.date.formatTurkishShort(),
                        style = MaterialTheme.typography.bodySmall,
                        color = CelestialTeal
                    )
                }
                TagChip(daysLabel(daysAway.toInt()))
            }
            Spacer(Modifier.size(10.dp))
            Text(
                event.description,
                style = MaterialTheme.typography.bodyMedium,
                color = TextPrimary.copy(alpha = 0.9f)
            )
            Spacer(Modifier.size(10.dp))
            Text(
                "Tavsiye",
                style = MaterialTheme.typography.labelMedium,
                color = NebulaPink
            )
            Text(
                event.advice,
                style = MaterialTheme.typography.bodyMedium,
                color = TextPrimary
            )
        }
    }
}

private fun daysLabel(days: Int): String = when (days) {
    0 -> "Bugün"
    1 -> "Yarın"
    else -> "$days gün"
}

private fun LocalDate.formatTurkishShort(): String {
    val months = arrayOf(
        "Oca","Şub","Mar","Nis","May","Haz","Tem","Ağu","Eyl","Eki","Kas","Ara"
    )
    return "$dayOfMonth ${months[monthNumber-1]} $year"
}
