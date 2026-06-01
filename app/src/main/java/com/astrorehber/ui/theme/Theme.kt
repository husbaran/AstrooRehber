package com.astrorehber.ui.theme

import android.app.Activity
import android.os.Build
import android.view.View
import android.view.WindowInsetsController
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView

private val Color_Error = androidx.compose.ui.graphics.Color(0xFFE57373)

private val AstroColorScheme = darkColorScheme(
    primary = StarGold,
    onPrimary = NightDeep,
    primaryContainer = MysticPurpleSoft,
    onPrimaryContainer = TextPrimary,
    secondary = MysticPurple,
    onSecondary = TextPrimary,
    secondaryContainer = NightHigh,
    onSecondaryContainer = TextPrimary,
    tertiary = NebulaPink,
    onTertiary = NightDeep,
    background = NightDeep,
    onBackground = TextPrimary,
    surface = NightMid,
    onSurface = TextPrimary,
    surfaceVariant = SurfaceCard,
    onSurfaceVariant = TextSecondary,
    outline = Divider,
    outlineVariant = NightHigh,
    error = Color_Error,
    onError = TextPrimary
)

val NightGradient: Brush
    get() = Brush.verticalGradient(
        colors = listOf(NightDeep, NightMid, NightSoft)
    )

@Composable
fun AstroRehberTheme(
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = NightDeep.toArgb()
            window.navigationBarColor = NightDeep.toArgb()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                window.insetsController?.setSystemBarsAppearance(
                    0,
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                )
            } else {
                @Suppress("DEPRECATION")
                view.systemUiVisibility = view.systemUiVisibility and
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
            }
        }
    }

    MaterialTheme(
        colorScheme = AstroColorScheme,
        typography = AstroTypography
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(NightGradient)
        ) {
            content()
        }
    }
}
