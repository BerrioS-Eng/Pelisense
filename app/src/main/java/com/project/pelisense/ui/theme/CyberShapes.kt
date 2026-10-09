package com.project.pelisense.ui.theme

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

/**
 * Custom shape with 45-degree chamfered corners.
 * Commonly used in PELISENSE Cyber-Jewel design system.
 */
class ChamferedShape(
    val topLeftCut: Dp = 0.dp,
    val topRightCut: Dp = 0.dp,
    val bottomRightCut: Dp = 0.dp,
    val bottomLeftCut: Dp = 0.dp
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val topL = with(density) { topLeftCut.toPx() }
        val topR = with(density) { topRightCut.toPx() }
        val botR = with(density) { bottomRightCut.toPx() }
        val botL = with(density) { bottomLeftCut.toPx() }

        val path = Path().apply {
            moveTo(topL, 0f)
            lineTo(size.width - topR, 0f)
            lineTo(size.width, topR)
            lineTo(size.width, size.height - botR)
            lineTo(size.width - botR, size.height)
            lineTo(botL, size.height)
            lineTo(0f, size.height - botL)
            lineTo(0f, topL)
            close()
        }
        return Outline.Generic(path)
    }
}

/**
 * Standard Cyber Card Shape with Top-Right and Bottom-Left chamfers.
 */
fun cyberCardShape(cutSize: Dp = 16.dp): Shape = ChamferedShape(
    topLeftCut = 0.dp,
    topRightCut = cutSize,
    bottomRightCut = 0.dp,
    bottomLeftCut = cutSize
)

/**
 * Top-Right cut shape for buttons and top-aligned tags.
 */
fun cyberButtonShape(cutSize: Dp = 12.dp): Shape = ChamferedShape(
    topLeftCut = 0.dp,
    topRightCut = cutSize,
    bottomRightCut = 0.dp,
    bottomLeftCut = 0.dp
)

/**
 * Diamond shape for tone and energy cards.
 */
val DiamondShape: Shape = object : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            moveTo(size.width / 2f, 0f)
            lineTo(size.width, size.height / 2f)
            lineTo(size.width / 2f, size.height)
            lineTo(0f, size.height / 2f)
            close()
        }
        return Outline.Generic(path)
    }
}
