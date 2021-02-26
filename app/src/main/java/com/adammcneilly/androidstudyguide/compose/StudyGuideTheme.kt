package com.adammcneilly.androidstudyguide.compose

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

@Composable
fun StudyGuideTheme(
    isInDarkMode: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorsToUse = if (isInDarkMode) {
        darkColors()
    } else {
        lightColors()
    }

    MaterialTheme(
        colors = colorsToUse
    ) {
        content()
    }
}
