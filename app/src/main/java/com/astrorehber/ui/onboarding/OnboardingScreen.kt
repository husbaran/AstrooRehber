package com.astrorehber.ui.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.astrorehber.astro.ZodiacSign
import com.astrorehber.data.UserProfile
import com.astrorehber.ui.components.GlassCard
import com.astrorehber.ui.components.OutlinedTextFieldDefaultsAstro
import com.astrorehber.ui.components.SectionTitle
import com.astrorehber.ui.components.SignPicker
import com.astrorehber.ui.theme.NightDeep
import com.astrorehber.ui.theme.StarGold
import com.astrorehber.ui.theme.TextPrimary
import com.astrorehber.ui.theme.TextSecondary

@Composable
fun OnboardingScreen(
    initial: UserProfile? = null,
    onCancel: (() -> Unit)? = null,
    onSubmit: (UserProfile) -> Unit
) {
    val isEdit = initial != null

    var name by remember { mutableStateOf(initial?.name.orEmpty()) }
    var sunSign by remember { mutableStateOf(initial?.sunSign) }
    var moonSign by remember { mutableStateOf(initial?.moonSign) }
    var ascendantSign by remember { mutableStateOf(initial?.ascendantSign) }
    var venusSign by remember { mutableStateOf(initial?.venusSign) }
    var error by remember { mutableStateOf<String?>(null) }

    val scroll = rememberScrollState()

    Column(
        Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars)
            .verticalScroll(scroll)
            .padding(horizontal = 20.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isEdit && onCancel != null) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onCancel) {
                    Icon(Icons.Outlined.ArrowBack, "Geri", tint = TextPrimary)
                }
                Spacer(Modifier.height(0.dp))
            }
        } else {
            Icon(
                Icons.Outlined.AutoAwesome,
                null,
                tint = StarGold,
                modifier = Modifier.padding(top = 12.dp)
            )
            Spacer(Modifier.height(12.dp))
        }

        Text(
            if (isEdit) "Profilini Düzenle" else "AstroRehber",
            style = MaterialTheme.typography.displayMedium,
            color = TextPrimary,
            fontWeight = FontWeight.Light
        )
        Text(
            if (isEdit) "Burçlarını güncelleyebilirsin"
            else "Burçlarını bir kez gir, her gün sana özel rehberlik al",
            style = MaterialTheme.typography.bodyMedium,
            color = TextSecondary
        )

        Spacer(Modifier.height(28.dp))

        GlassCard(Modifier.fillMaxWidth()) {
            Column {
                SectionTitle("Astrolojik Profilin")
                Spacer(Modifier.height(16.dp))

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("İsim") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaultsAstro()
                )

                Spacer(Modifier.height(16.dp))
                SignPicker("Güneş Burcu", sunSign, onSelected = { sunSign = it })
                Spacer(Modifier.height(12.dp))
                SignPicker("Ay Burcu", moonSign, onSelected = { moonSign = it })
                Spacer(Modifier.height(12.dp))
                SignPicker("Yükselen Burcu", ascendantSign, onSelected = { ascendantSign = it })
                Spacer(Modifier.height(12.dp))
                SignPicker("Venüs Burcu", venusSign, onSelected = { venusSign = it })

                if (error != null) {
                    Spacer(Modifier.height(12.dp))
                    Text(
                        error!!,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Spacer(Modifier.height(24.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    if (isEdit && onCancel != null) {
                        OutlinedButton(
                            onClick = onCancel,
                            modifier = Modifier.weight(1f).heightIn(min = 52.dp),
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Text("İptal")
                        }
                    }
                    Button(
                        onClick = {
                            when {
                                name.isBlank() -> error = "İsmini yazar mısın?"
                                sunSign == null -> error = "Güneş burcunu seç"
                                moonSign == null -> error = "Ay burcunu seç"
                                ascendantSign == null -> error = "Yükselen burcunu seç"
                                venusSign == null -> error = "Venüs burcunu seç"
                                else -> {
                                    error = null
                                    onSubmit(
                                        UserProfile(
                                            name = name.trim(),
                                            sunSign = sunSign!!,
                                            moonSign = moonSign!!,
                                            ascendantSign = ascendantSign!!,
                                            venusSign = venusSign!!
                                        )
                                    )
                                }
                            }
                        },
                        modifier = Modifier.weight(1f).heightIn(min = 52.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = StarGold,
                            contentColor = NightDeep
                        )
                    ) {
                        Text(
                            if (isEdit) "Kaydet" else "Kaydet ve Başla",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(20.dp))
        Text(
            "Burçlarını bilmiyorsan ücretsiz doğum haritası sitelerinden öğrenebilirsin. Bilgilerin sadece cihazında saklanır.",
            style = MaterialTheme.typography.bodySmall,
            color = TextSecondary,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        Spacer(Modifier.height(24.dp))
    }
}
