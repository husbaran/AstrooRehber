package com.astrorehber.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.astrorehber.astro.ZodiacSign

@Composable
fun SignPicker(
    label: String,
    selected: ZodiacSign?,
    onSelected: (ZodiacSign) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val display = selected?.let { "${it.glyph}  ${it.turkishName}" } ?: ""

    Box(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = display,
            onValueChange = {},
            readOnly = true,
            enabled = false,
            label = { Text(label) },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true },
            colors = OutlinedTextFieldDefaultsAstro()
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            ZodiacSign.entries.forEach { sign ->
                DropdownMenuItem(
                    text = {
                        Text(
                            "${sign.glyph}  ${sign.turkishName}",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    onClick = {
                        onSelected(sign)
                        expanded = false
                    }
                )
            }
        }
    }
}
