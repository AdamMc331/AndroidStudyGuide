package com.adammcneilly.androidstudyguide.compose

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

// Palette: https://coolors.co/7ae7c7-75bba7-6d819c-795663-645244

val SlateGray = Color(0xFF6C809A)
val DeepTaupe = Color(0xFF795663)
val Cultured = Color(0xFFF3F5F7)

val StudyGuideLightColors = lightColors(
    primary = SlateGray,
    secondary = DeepTaupe,
    background = Cultured,
)

val StudyGuideDarkColors = darkColors(
    primary = SlateGray,
    secondary = DeepTaupe,
)
