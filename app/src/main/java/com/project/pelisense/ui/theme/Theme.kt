package com.project.pelisense.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val CyberColorScheme = darkColorScheme(
    primary = CyberPrimary,
    onPrimary = CyberOnPrimary,
    primaryContainer = CyberPrimaryContainer,
    onPrimaryContainer = CyberOnPrimaryContainer,
    secondary = CyberSecondary,
    onSecondary = CyberOnSecondary,
    secondaryContainer = CyberSecondaryContainer,
    background = CyberBackground,
    onBackground = CyberOnSurface,
    surface = CyberSurface,
    onSurface = CyberOnSurface,
    surfaceVariant = CyberSurfaceContainerHigh,
    onSurfaceVariant = CyberOnSurfaceVariant,
    outline = CyberOutline,
    outlineVariant = CyberOutlineVariant
)

@Composable
fun PelisenseTheme(
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = CyberBackground.toArgb()
            window.navigationBarColor = CyberBackground.toArgb()
            
            val insetsController = WindowCompat.getInsetsController(window, view)
            insetsController.isAppearanceLightStatusBars = false
            insetsController.isAppearanceLightNavigationBars = false
        }
    }

    MaterialTheme(
        colorScheme = CyberColorScheme,
        typography = Typography,
        content = content
    )
}
