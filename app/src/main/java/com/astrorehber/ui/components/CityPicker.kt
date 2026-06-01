package com.astrorehber.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Public
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.astrorehber.data.City
import com.astrorehber.data.CityDatabase
import com.astrorehber.ui.theme.MysticPurple
import com.astrorehber.ui.theme.NightHigh
import com.astrorehber.ui.theme.SurfaceCard
import com.astrorehber.ui.theme.SurfaceCardHigh
import com.astrorehber.ui.theme.TextPrimary
import com.astrorehber.ui.theme.TextSecondary

@Composable
fun CityPicker(
    selected: City?,
    onSelected: (City) -> Unit,
    modifier: Modifier = Modifier
) {
    var query by remember { mutableStateOf("") }
    val results = remember(query) { CityDatabase.search(query, limit = 50) }

    Column(modifier = modifier) {
        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            placeholder = { Text("Şehir ara — örn. İstanbul") },
            singleLine = true,
            leadingIcon = { Icon(Icons.Outlined.Search, null) },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaultsAstro()
        )

        if (selected != null && query.isEmpty()) {
            Box(
                Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(MysticPurple.copy(alpha = 0.15f))
                    .padding(14.dp)
            ) {
                Column {
                    Text(
                        "Seçili: ${selected.displayName}",
                        style = MaterialTheme.typography.titleSmall,
                        color = TextPrimary
                    )
                    Text(
                        "${"%.2f".format(selected.latitude)}°  ·  ${"%.2f".format(selected.longitude)}°  ·  ${selected.timeZoneId}",
                        style = MaterialTheme.typography.bodySmall,
                        color = TextSecondary
                    )
                }
            }
        }

        if (query.isNotEmpty()) {
            LazyColumn(
                Modifier
                    .padding(top = 8.dp)
                    .heightIn(max = 280.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(SurfaceCardHigh)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(vertical = 4.dp)
            ) {
                if (results.isEmpty()) {
                    item {
                        Text(
                            "Sonuç yok",
                            color = TextSecondary,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                } else {
                    items(results) { city ->
                        CityRow(city) { onSelected(city); query = "" }
                    }
                }
            }
        }
    }
}

@Composable
private fun CityRow(city: City, onClick: () -> Unit) {
    androidx.compose.foundation.layout.Row(
        Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 14.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            Icons.Outlined.Public,
            null,
            tint = MysticPurple,
            modifier = Modifier.padding(end = 12.dp)
        )
        Column(Modifier.fillMaxHeight()) {
            Text(city.name, color = TextPrimary, style = MaterialTheme.typography.bodyLarge)
            Text(
                city.country,
                color = TextSecondary,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
fun OutlinedTextFieldDefaultsAstro() = OutlinedTextFieldDefaults.colors(
    focusedContainerColor = SurfaceCard,
    unfocusedContainerColor = SurfaceCard,
    focusedBorderColor = MysticPurple,
    unfocusedBorderColor = NightHigh,
    focusedLabelColor = MysticPurple,
    unfocusedLabelColor = TextSecondary,
    focusedTextColor = TextPrimary,
    unfocusedTextColor = TextPrimary,
    cursorColor = MysticPurple
)
