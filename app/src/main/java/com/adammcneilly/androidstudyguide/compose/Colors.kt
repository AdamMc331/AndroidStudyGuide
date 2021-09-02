@file:Suppress("MagicNumber")

package com.adammcneilly.androidstudyguide.compose

import androidx.compose.material.Colors
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

// Palette: https://coolors.co/7ae7c7-75bba7-6d819c-795663-645244

val SlateGray = Color(0xFF6C809A)
val DeepTaupe = Color(0xFF795663)
val Cultured = Color(0xFFF3F5F7)
val GreenSheen = Color(0xFF75BBA7)
val RichBlack = Color(0xFF101519)

private val LightStatusBar = Color(0xFF536379)
private val LightNavigationBar = SlateGray
private val DarkStatusBar = Color(0xFF1F2226)
private val DarkNavigationBar = Color(0xFF2D3134)

val Colors.statusBarColor: Color
    get() = if (isLight) LightStatusBar else DarkStatusBar

val Colors.navigationBarColor: Color
    get() = if (isLight) LightNavigationBar else DarkNavigationBar

val StudyGuideLightColors = lightColors(
    primary = SlateGray,
    secondary = DeepTaupe,
    background = Cultured,
)

val StudyGuideDarkColors = darkColors(
    primary = SlateGray,
    secondary = GreenSheen,
    surface = RichBlack,
)
