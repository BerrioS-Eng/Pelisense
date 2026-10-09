package com.project.pelisense.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.pelisense.data.Movie
import com.project.pelisense.ui.components.CyberBadge
import com.project.pelisense.ui.components.CyberButton
import com.project.pelisense.ui.components.CyberGlassCard
import com.project.pelisense.ui.components.CyberOutlinedButton
import com.project.pelisense.ui.components.CyberTopBar
import com.project.pelisense.ui.theme.ChamferedShape
import com.project.pelisense.ui.theme.CyberBackground
import com.project.pelisense.ui.theme.CyberOnSurface
import com.project.pelisense.ui.theme.CyberOnSurfaceVariant
import com.project.pelisense.ui.theme.CyberPrimary
import com.project.pelisense.ui.theme.CyberPrimaryBright
import com.project.pelisense.ui.theme.TechFontFamily

@Composable
fun MovieDetailsScreen(
    movie: Movie,
    onBackClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    Scaffold(
        topBar = {
            CyberTopBar(
                title = "PELISENSE",
                onBackClick = onBackClick,
                actions = {
                    IconButton(onClick = onProfileClick) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF0F3E3E))
                                .border(1.dp, CyberPrimary, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Profile",
                                tint = CyberPrimary,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }
            )
        },
        containerColor = CyberBackground
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // HERO BACKDROP & MOVIE TITLE
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(340.dp)
                        .background(Color(movie.posterColor))
                ) {
                    // Futuristic Background Pattern
                    Canvas(modifier = Modifier.fillMaxSize()) {
                        drawPath(
                            path = Path().apply {
                                moveTo(0f, 0f)
                                lineTo(size.width, size.height * 0.7f)
                                lineTo(size.width, size.height)
                                lineTo(0f, size.height)
                                close()
                            },
                            color = Color(0x33003737)
                        )
                    }

                    // Gradient Overlay to dark
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        Color(0x88021616),
                                        CyberBackground
                                    )
                                )
                            )
                    )

                    // Movie Info on top of banner
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(20.dp)
                    ) {
                        Text(
                            text = movie.title,
                            style = MaterialTheme.typography.displayLarge.copy(
                                fontFamily = TechFontFamily,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 2.sp
                            ),
                            color = CyberPrimaryBright
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // Badges Row
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            CyberBadge(text = movie.year)
                            CyberBadge(text = movie.duration)
                            CyberBadge(text = movie.mpaaRating)
                            CyberBadge(text = "⭐ ${movie.rating}", icon = Icons.Default.Star)
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        // WATCH NOW Button
                        CyberButton(
                            text = "WATCH NOW",
                            onClick = { },
                            icon = Icons.Default.PlayArrow,
                            modifier = Modifier.fillMaxWidth(0.6f)
                        )
                    }
                }
            }

            // SECTION 1: The Story
            item {
                Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)) {
                    CyberGlassCard(
                        modifier = Modifier.fillMaxWidth(),
                        shape = ChamferedShape(topRightCut = 16.dp, bottomLeftCut = 16.dp)
                    ) {
                        Column(modifier = Modifier.padding(20.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.List,
                                    contentDescription = null,
                                    tint = CyberPrimary,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "The Story",
                                    style = MaterialTheme.typography.headlineMedium.copy(
                                        fontFamily = TechFontFamily,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    color = CyberPrimaryBright
                                )
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            Text(
                                text = movie.synopsis,
                                style = MaterialTheme.typography.bodyLarge,
                                color = CyberOnSurfaceVariant
                            )
                        }
                    }
                }
            }

            // SECTION 2: Lead Cast
            item {
                Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)) {
                    CyberGlassCard(
                        modifier = Modifier.fillMaxWidth(),
                        shape = ChamferedShape(topRightCut = 16.dp, bottomLeftCut = 16.dp)
                    ) {
                        Column(modifier = Modifier.padding(20.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Person,
                                    contentDescription = null,
                                    tint = CyberPrimary,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Lead Cast",
                                    style = MaterialTheme.typography.headlineMedium.copy(
                                        fontFamily = TechFontFamily,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    color = CyberPrimaryBright
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                movie.cast.forEach { castMember ->
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        modifier = Modifier.weight(1f)
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(64.dp)
                                                .clip(CircleShape)
                                                .background(Color(0xFF0C2F30))
                                                .border(1.dp, CyberPrimary, CircleShape),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Person,
                                                contentDescription = null,
                                                tint = CyberPrimary,
                                                modifier = Modifier.size(32.dp)
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(8.dp))
                                        Text(
                                            text = castMember.name,
                                            style = MaterialTheme.typography.labelSmall.copy(
                                                fontFamily = TechFontFamily,
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 11.sp
                                            ),
                                            color = CyberOnSurface
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // ACTION BUTTONS: WATCHLIST & SHARE
            item {
                Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)) {
                    CyberOutlinedButton(
                        text = "+ WATCHLIST",
                        onClick = { },
                        icon = Icons.Default.Add,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    CyberOutlinedButton(
                        text = "SHARE DATA",
                        onClick = { },
                        icon = Icons.Default.Share,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            // SECTION 3: Details
            item {
                Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)) {
                    CyberGlassCard(
                        modifier = Modifier.fillMaxWidth(),
                        shape = ChamferedShape(topRightCut = 16.dp, bottomLeftCut = 16.dp)
                    ) {
                        Column(modifier = Modifier.padding(20.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Info,
                                    contentDescription = null,
                                    tint = CyberPrimary,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Details",
                                    style = MaterialTheme.typography.headlineMedium.copy(
                                        fontFamily = TechFontFamily,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    color = CyberPrimaryBright
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            DetailRow(label = "DIRECTOR", value = movie.director)
                            Spacer(modifier = Modifier.height(12.dp))
                            DetailRow(label = "GENRE", value = movie.genres.joinToString(" • "))
                            Spacer(modifier = Modifier.height(12.dp))
                            DetailRow(label = "BUDGET", value = movie.budget)
                            Spacer(modifier = Modifier.height(12.dp))
                            DetailRow(label = "GLOBAL REVENUE", value = movie.globalRevenue)
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Composable
private fun DetailRow(label: String, value: String) {
    Column {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall.copy(
                fontFamily = TechFontFamily,
                fontWeight = FontWeight.Bold
            ),
            color = CyberOnSurfaceVariant
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Medium
            ),
            color = CyberPrimaryBright
        )
    }
}
