package com.workly.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val WorklyColors = lightColorScheme(
    primary = BluePrimary,
    secondary = BlueDark,
    background = BackgroundLight,
    surface = SurfaceLight,
    onPrimary = Color.White,
    onBackground = TextPrimary,
    onSurface = TextPrimary
)

@Composable
fun WorklyTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = WorklyColors,
        content = content
    )
}