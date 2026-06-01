package com.astrorehber.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val Display = FontFamily.Serif
private val Body = FontFamily.SansSerif

val AstroTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = Display, fontWeight = FontWeight.Light,
        fontSize = 48.sp, lineHeight = 56.sp, letterSpacing = (-0.5).sp
    ),
    displayMedium = TextStyle(
        fontFamily = Display, fontWeight = FontWeight.Normal,
        fontSize = 36.sp, lineHeight = 44.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = Display, fontWeight = FontWeight.Medium,
        fontSize = 28.sp, lineHeight = 36.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = Display, fontWeight = FontWeight.Medium,
        fontSize = 22.sp, lineHeight = 30.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = Display, fontWeight = FontWeight.Medium,
        fontSize = 18.sp, lineHeight = 24.sp
    ),
    titleLarge = TextStyle(
        fontFamily = Body, fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp, lineHeight = 24.sp, letterSpacing = 0.15.sp
    ),
    titleMedium = TextStyle(
        fontFamily = Body, fontWeight = FontWeight.Medium,
        fontSize = 15.sp, lineHeight = 22.sp, letterSpacing = 0.15.sp
    ),
    titleSmall = TextStyle(
        fontFamily = Body, fontWeight = FontWeight.Medium,
        fontSize = 13.sp, lineHeight = 20.sp, letterSpacing = 0.1.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = Body, fontWeight = FontWeight.Normal,
        fontSize = 16.sp, lineHeight = 24.sp, letterSpacing = 0.4.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = Body, fontWeight = FontWeight.Normal,
        fontSize = 14.sp, lineHeight = 20.sp, letterSpacing = 0.25.sp
    ),
    bodySmall = TextStyle(
        fontFamily = Body, fontWeight = FontWeight.Normal,
        fontSize = 12.sp, lineHeight = 16.sp, letterSpacing = 0.4.sp
    ),
    labelLarge = TextStyle(
        fontFamily = Body, fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp, lineHeight = 18.sp, letterSpacing = 1.sp
    ),
    labelMedium = TextStyle(
        fontFamily = Body, fontWeight = FontWeight.Medium,
        fontSize = 12.sp, lineHeight = 16.sp, letterSpacing = 0.8.sp
    ),
    labelSmall = TextStyle(
        fontFamily = Body, fontWeight = FontWeight.Medium,
        fontSize = 11.sp, lineHeight = 14.sp, letterSpacing = 0.5.sp
    )
)
