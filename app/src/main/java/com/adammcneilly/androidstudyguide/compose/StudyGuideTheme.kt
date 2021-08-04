package com.adammcneilly.androidstudyguide.compose

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun StudyGuideTheme(
    isInDarkMode: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorsToUse = if (isInDarkMode) {
        StudyGuideDarkColors
    } else {
        StudyGuideLightColors
    }

    MaterialTheme(
        colors = colorsToUse
    ) {
        content()
    }
}
