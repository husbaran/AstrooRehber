package com.astrorehber.ui.chart

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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.astrorehber.astro.Element
import com.astrorehber.astro.ZodiacSign
import com.astrorehber.data.UserProfile
import com.astrorehber.ui.components.GlassCard
import com.astrorehber.ui.components.KeyValueRow
import com.astrorehber.ui.components.SectionTitle
import com.astrorehber.ui.theme.StarGold
import com.astrorehber.ui.theme.TextPrimary
import com.astrorehber.ui.theme.TextSecondary

@Composable
fun ProfileScreen(
    profile: UserProfile,
    onBack: () -> Unit
) {
    val scroll = rememberScrollState()

    Column(
        Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars)
            .verticalScroll(scroll)
            .padding(horizontal = 20.dp, vertical = 12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onBack) {
                Icon(Icons.Outlined.ArrowBack, "Geri", tint = TextPrimary)
            }
            Column {
                Text(
                    "Astrolojik Profilim",
                    style = MaterialTheme.typography.headlineSmall,
                    color = TextPrimary,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    profile.name,
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondary
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        GlassCard(Modifier.fillMaxWidth()) {
            Column {
                SectionTitle("Burçların")
                Spacer(Modifier.height(12.dp))
                SignDetailRow("Güneş", profile.sunSign, "Temel kimliğin ve yaşam enerjin")
                SignDetailRow("Ay", profile.moonSign, "Duygusal dünyan ve içsel ihtiyaçların")
                SignDetailRow("Yükselen", profile.ascendantSign, "Dışarıya yansıttığın ilk izlenim")
                SignDetailRow("Venüs", profile.venusSign, "Sevgi dili ve estetik zevkin")
            }
        }

        Spacer(Modifier.height(16.dp))

        GlassCard(Modifier.fillMaxWidth()) {
            Column {
                SectionTitle("Kısa Yorum")
                Spacer(Modifier.height(10.dp))
                Text(
                    profileSummary(profile),
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextPrimary
                )
            }
        }

        Spacer(Modifier.height(24.dp))
    }
}

@Composable
private fun SignDetailRow(label: String, sign: ZodiacSign, subtitle: String) {
    Row(
        Modifier.fillMaxWidth().padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            sign.glyph,
            style = MaterialTheme.typography.displaySmall,
            color = StarGold,
            modifier = Modifier.size(40.dp)
        )
        Spacer(Modifier.size(14.dp))
        Column(Modifier.weight(1f)) {
            Text(label, style = MaterialTheme.typography.labelMedium, color = TextSecondary)
            Text(sign.turkishName, style = MaterialTheme.typography.titleMedium, color = TextPrimary, fontWeight = FontWeight.Medium)
            Text(subtitle, style = MaterialTheme.typography.bodySmall, color = TextSecondary)
        }
        Text(elementLabel(sign.element), style = MaterialTheme.typography.labelSmall, color = StarGold)
    }
}

private fun elementLabel(element: Element): String = when (element) {
    Element.ATES -> "Ateş"
    Element.TOPRAK -> "Toprak"
    Element.HAVA -> "Hava"
    Element.SU -> "Su"
}

private fun profileSummary(profile: UserProfile): String = buildString {
    append("Güneş'in ${profile.sunSign.turkishName} burcunda olması ")
    append("temel kimliğine ${elementLabel(profile.sunSign.element).lowercase()} bir enerji katar. ")
    append("Ay'ın ${profile.moonSign.turkishName} burcunda olması duygusal dünyana ")
    append("${profile.moonSign.modality.turkishName} bir nitelik verir. ")
    append("Yükselen ${profile.ascendantSign.turkishName}, çevreyle ilk temasını ")
    append("${elementLabel(profile.ascendantSign.element).lowercase()} bir filtreyle şekillendirir. ")
    append("Venüs'ün ${profile.venusSign.turkishName} burcunda olması sevgi ve estetik ")
    append("tercihlerini bu burcun dokunuşuyla ifade eder.")
}
