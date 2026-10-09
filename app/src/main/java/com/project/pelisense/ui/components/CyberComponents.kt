package com.project.pelisense.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CompassCalibration
import androidx.compose.material.icons.filled.Diamond
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Mood
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.pelisense.ui.theme.ChamferedShape
import com.project.pelisense.ui.theme.CyberBackground
import com.project.pelisense.ui.theme.CyberChipBg
import com.project.pelisense.ui.theme.CyberGlassBg
import com.project.pelisense.ui.theme.CyberGlassBorder
import com.project.pelisense.ui.theme.CyberOnPrimary
import com.project.pelisense.ui.theme.CyberOutline
import com.project.pelisense.ui.theme.CyberOutlineVariant
import com.project.pelisense.ui.theme.CyberPrimary
import com.project.pelisense.ui.theme.CyberPrimaryBright
import com.project.pelisense.ui.theme.TechFontFamily
import com.project.pelisense.ui.theme.cyberButtonShape
import com.project.pelisense.ui.theme.cyberCardShape

/**
 * Primary Cyber Button with solid cyan fill and chamfered shape.
 */
@Composable
fun CyberButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    shape: Shape = cyberButtonShape(12.dp)
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(48.dp),
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = CyberPrimaryBright,
            contentColor = CyberOnPrimary
        ),
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp),
                    tint = CyberOnPrimary
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(
                text = text.uppercase(),
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontFamily = TechFontFamily
                ),
                color = CyberOnPrimary
            )
        }
    }
}

/**
 * Ghost/Outlined Cyber Button with 1px Cyan border and transparent background.
 */
@Composable
fun CyberOutlinedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    shape: Shape = cyberButtonShape(8.dp)
) {
    Surface(
        modifier = modifier
            .height(44.dp)
            .clip(shape)
            .border(1.dp, CyberPrimary, shape)
            .clickable { onClick() },
        color = Color.Transparent
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = CyberPrimary
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(
                text = text.uppercase(),
                style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontFamily = TechFontFamily
                ),
                color = CyberPrimary
            )
        }
    }
}

/**
 * Glassmorphic Card container with chamfered corners and light catch border.
 */
@Composable
fun CyberGlassCard(
    modifier: Modifier = Modifier,
    shape: Shape = cyberCardShape(16.dp),
    backgroundColor: Color = CyberGlassBg,
    borderColor: Color = CyberGlassBorder,
    borderWidth: Dp = 1.dp,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .clip(shape)
            .background(backgroundColor)
            .border(borderWidth, borderColor, shape)
    ) {
        content()
    }
}

/**
 * Chip tag component for genres and filters.
 */
@Composable
fun CyberChip(
    text: String,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    val bg = if (isSelected) CyberPrimary else CyberChipBg
    val textColor = if (isSelected) CyberOnPrimary else CyberPrimary
    val border = if (isSelected) CyberPrimary else CyberOutlineVariant

    val shape = ChamferedShape(topRightCut = 6.dp)

    Box(
        modifier = modifier
            .clip(shape)
            .background(bg)
            .border(1.dp, border, shape)
            .then(if (onClick != null) Modifier.clickable { onClick() } else Modifier)
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Text(
            text = text.uppercase(),
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 11.sp,
                fontFamily = TechFontFamily
            ),
            color = textColor
        )
    }
}

/**
 * Match badge tag (e.g. "98% MOOD MATCH").
 */
@Composable
fun CyberBadge(
    text: String,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null
) {
    val shape = ChamferedShape(topRightCut = 8.dp)
    Box(
        modifier = modifier
            .clip(shape)
            .background(Color(0xE60A2F2F))
            .border(1.dp, CyberPrimary, shape)
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = CyberPrimary,
                    modifier = Modifier.size(12.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
            }
            Text(
                text = text.uppercase(),
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold,
                    fontFamily = TechFontFamily
                ),
                color = CyberPrimary
            )
        }
    }
}

/**
 * Laser Line Divider with gradient fade at ends.
 */
@Composable
fun LaserDivider(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color.Transparent,
                        CyberGlassBorder,
                        Color.Transparent
                    )
                )
            )
    )
}

/**
 * Top App Bar with Pelisense branding.
 */
@Composable
fun CyberTopBar(
    title: String = "PELISENSE",
    onBackClick: (() -> Unit)? = null,
    actions: @Composable (RowScope.() -> Unit)? = null
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(CyberBackground)
            .statusBarsPadding()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (onBackClick != null) {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver",
                            tint = CyberPrimary
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                } else {
                    Icon(
                        imageVector = Icons.Default.Diamond,
                        contentDescription = null,
                        tint = CyberPrimary,
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Text(
                    text = title.uppercase(),
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Bold,
                        fontFamily = TechFontFamily,
                        letterSpacing = 2.sp
                    ),
                    color = CyberPrimary
                )
            }

            if (actions != null) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    actions()
                }
            }
        }
    }
}

/**
 * Bottom Navigation Bar with 4 tabs: Mood, Explore, History, Profile.
 */
enum class CyberTab(val label: String, val icon: ImageVector) {
    MOOD("Mood", Icons.Default.Mood),
    EXPLORE("Explore", Icons.Default.CompassCalibration),
    HISTORY("History", Icons.Default.History),
    PROFILE("Profile", Icons.Default.Person)
}

@Composable
fun CyberBottomBar(
    currentTab: CyberTab,
    onTabSelected: (CyberTab) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(CyberBackground)
            .navigationBarsPadding()
    ) {
        LaserDivider()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CyberTab.entries.forEach { tab ->
                val isSelected = tab == currentTab
                val tint = if (isSelected) CyberPrimary else CyberOutline

                Column(
                    modifier = Modifier
                        .clickable { onTabSelected(tab) }
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = tab.icon,
                        contentDescription = tab.label,
                        tint = tint,
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = tab.label,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontSize = 10.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                            fontFamily = TechFontFamily
                        ),
                        color = tint
                    )
                    if (isSelected) {
                        Spacer(modifier = Modifier.height(2.dp))
                        Box(
                            modifier = Modifier
                                .size(4.dp)
                                .clip(CircleShape)
                                .background(CyberPrimary)
                        )
                    }
                }
            }
        }
    }
}
