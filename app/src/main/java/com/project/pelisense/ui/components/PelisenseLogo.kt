package com.project.pelisense.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.project.pelisense.R

/**
 * Función @Composable para mostrar en pantalla el logo guardado en @drawable/pelisense_logo.
 */
@Composable
fun PelisenseLogoImage(
    modifier: Modifier = Modifier,
    size: Dp = 100.dp,
    contentDescription: String? = "Pelisense Logo"
) {
    Image(
        painter = painterResource(id = R.drawable.pelisense_logo),
        contentDescription = contentDescription,
        contentScale = ContentScale.Fit,
        modifier = modifier.size(size)
    )
}

/**
 * Alias de compatibilidad para usar en los componentes existentes de la app.
 */
@Composable
fun PelisenseLogoIcon(
    modifier: Modifier = Modifier,
    size: Dp = 80.dp
) {
    PelisenseLogoImage(
        modifier = modifier,
        size = size
    )
}
