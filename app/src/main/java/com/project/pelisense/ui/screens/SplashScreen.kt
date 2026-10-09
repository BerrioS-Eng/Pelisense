package com.project.pelisense.ui.screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.pelisense.ui.components.PelisenseLogoIcon
import com.project.pelisense.ui.theme.ChamferedShape
import com.project.pelisense.ui.theme.CyberBackground
import com.project.pelisense.ui.theme.CyberGlassBorder
import com.project.pelisense.ui.theme.CyberOutline
import com.project.pelisense.ui.theme.CyberPrimary
import com.project.pelisense.ui.theme.CyberPrimaryBright
import com.project.pelisense.ui.theme.TechFontFamily
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNavigateNext: () -> Unit
) {
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(durationMillis = 600),
        label = "SplashFade"
    )

    // Infinite transition for continuous 360-degree rotation & pulsing logo
    val infiniteTransition = rememberInfiniteTransition(label = "SplashInfinite")
    
    // Continuous clockwise rotation angle for outer arc
    val rotationClockwise by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "RotationCW"
    )

    // Continuous counter-clockwise rotation angle for inner arc
    val rotationCounterClockwise by infiniteTransition.animateFloat(
        initialValue = 360f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1400, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "RotationCCW"
    )

    // Pulsing scale for the logo box
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 0.97f,
        targetValue = 1.03f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 900, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "PulseAnim"
    )

    // Animated dots counter for "INITIALIZING SYSTEM..."
    var dotCount by remember { mutableIntStateOf(1) }

    LaunchedEffect(Unit) {
        startAnimation = true
        // Keep splash active for 2.8 seconds so user experiences the spinning loader
        repeat(9) {
            delay(300)
            dotCount++
        }
        onNavigateNext()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(CyberBackground)
            .systemBarsPadding(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.alpha(alphaAnim)
        ) {
            // Official high quality logo pelisense_logo.png
            PelisenseLogoIcon(
                size = 130.dp,
                modifier = Modifier.scale(pulseScale)
            )

            Spacer(modifier = Modifier.height(28.dp))

            // Glowing Box with PELISENSE text
            val boxShape = ChamferedShape(topRightCut = 12.dp, bottomLeftCut = 12.dp)
            Box(
                modifier = Modifier
                    .size(width = 250.dp, height = 70.dp)
                    .scale(pulseScale)
                    .background(Color(0xFF032222), boxShape)
                    .border(
                        width = 1.dp,
                        brush = Brush.horizontalGradient(
                            listOf(CyberPrimary, CyberGlassBorder, CyberPrimary)
                        ),
                        shape = boxShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "PELISENSE",
                    style = MaterialTheme.typography.displayMedium.copy(
                        fontFamily = TechFontFamily,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 4.sp
                    ),
                    color = CyberPrimaryBright
                )
            }

            Spacer(modifier = Modifier.height(48.dp))

            // DUAL-RING DYNAMIC ROTATING LOADER ("Dando vueltas")
            Box(
                modifier = Modifier.size(64.dp),
                contentAlignment = Alignment.Center
            ) {
                // Native Material 3 spinning indicator
                CircularProgressIndicator(
                    modifier = Modifier.size(56.dp),
                    color = CyberPrimaryBright,
                    strokeWidth = 3.dp,
                    trackColor = Color(0xFF092B2D)
                )

                // Canvas with dual counter-rotating cyan laser arcs
                Canvas(modifier = Modifier.fillMaxSize()) {
                    // Outer clockwise arc
                    rotate(rotationClockwise) {
                        drawArc(
                            color = CyberPrimaryBright,
                            startAngle = 0f,
                            sweepAngle = 120f,
                            useCenter = false,
                            style = Stroke(width = 3.5.dp.toPx(), cap = StrokeCap.Round)
                        )
                    }

                    // Inner counter-clockwise arc
                    rotate(rotationCounterClockwise) {
                        drawArc(
                            color = Color(0xFF88FFFF),
                            startAngle = 180f,
                            sweepAngle = 90f,
                            useCenter = false,
                            style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // Animated Loading Text ("INITIALIZING SYSTEM...")
            val dotsText = ".".repeat((dotCount % 3) + 1)
            Text(
                text = "INITIALIZING SYSTEM$dotsText",
                style = MaterialTheme.typography.labelMedium.copy(
                    fontFamily = TechFontFamily,
                    letterSpacing = 2.5.sp,
                    fontWeight = FontWeight.Medium
                ),
                color = CyberOutline
            )
        }
    }
}
