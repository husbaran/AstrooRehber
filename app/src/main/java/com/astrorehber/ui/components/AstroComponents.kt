package com.astrorehber.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.astrorehber.ui.theme.Divider
import com.astrorehber.ui.theme.MysticPurple
import com.astrorehber.ui.theme.NightHigh
import com.astrorehber.ui.theme.StarGold
import com.astrorehber.ui.theme.StarGoldSoft
import com.astrorehber.ui.theme.SurfaceCard
import com.astrorehber.ui.theme.SurfaceCardHigh
import com.astrorehber.ui.theme.TextSecondary

@Composable
fun GlassCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(
                Brush.verticalGradient(
                    colors = listOf(SurfaceCardHigh, SurfaceCard)
                )
            )
            .border(1.dp, NightHigh, RoundedCornerShape(20.dp))
            .padding(20.dp)
    ) {
        content()
    }
}

@Composable
fun SectionTitle(
    text: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            Modifier
                .size(6.dp)
                .clip(CircleShape)
                .background(StarGold)
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = text.uppercase(),
            style = MaterialTheme.typography.labelLarge,
            color = StarGoldSoft,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(Modifier.width(12.dp))
        Box(
            Modifier
                .weight(1f)
                .height(1.dp)
                .background(Divider)
        )
    }
}

@Composable
fun KeyValueRow(label: String, value: String) {
    Row(
        Modifier.fillMaxWidth().padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            label,
            style = MaterialTheme.typography.bodyMedium,
            color = TextSecondary
        )
        Text(
            value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun TagChip(text: String) {
    Box(
        Modifier
            .clip(RoundedCornerShape(50))
            .background(MysticPurple.copy(alpha = 0.18f))
            .border(1.dp, MysticPurple.copy(alpha = 0.45f), RoundedCornerShape(50))
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text,
            style = MaterialTheme.typography.labelMedium,
            color = MysticPurple
        )
    }
}

@Composable
fun StarRow() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        repeat(3) {
            Text(" ✦ ", color = StarGoldSoft, style = MaterialTheme.typography.bodySmall)
        }
    }
}
