package com.project.pelisense.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Mood
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Person
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.pelisense.data.Movie
import com.project.pelisense.data.SampleData
import com.project.pelisense.ui.components.CyberBadge
import com.project.pelisense.ui.components.CyberBottomBar
import com.project.pelisense.ui.components.CyberButton
import com.project.pelisense.ui.components.CyberChip
import com.project.pelisense.ui.components.CyberGlassCard
import com.project.pelisense.ui.components.CyberTab
import com.project.pelisense.ui.components.CyberTopBar
import com.project.pelisense.ui.theme.ChamferedShape
import com.project.pelisense.ui.theme.CyberBackground
import com.project.pelisense.ui.theme.CyberOnSurface
import com.project.pelisense.ui.theme.CyberOnSurfaceVariant
import com.project.pelisense.ui.theme.CyberPrimary
import com.project.pelisense.ui.theme.CyberPrimaryBright
import com.project.pelisense.ui.theme.TechFontFamily

@Composable
fun HomeScreen(
    onMovieClick: (Movie) -> Unit,
    onProfileClick: () -> Unit,
    onTabSelected: (CyberTab) -> Unit
) {
    Scaffold(
        topBar = {
            CyberTopBar(
                title = "PELISENSE",
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
        bottomBar = {
            CyberBottomBar(
                currentTab = CyberTab.EXPLORE,
                onTabSelected = onTabSelected
            )
        },
        containerColor = CyberBackground
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // MOOD ANALYSIS COMPLETE Subheader
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Mood,
                        contentDescription = null,
                        tint = CyberPrimary,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "MOOD ANALYSIS COMPLETE",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontFamily = TechFontFamily,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 2.sp
                        ),
                        color = CyberPrimaryBright
                    )
                }
            }

            // Hero Movie Card: NEON SYNTHESIS / NEON HORIZON
            item {
                HeroMovieCard(
                    movie = SampleData.heroMovie,
                    onMovieClick = { onMovieClick(SampleData.heroMovie) }
                )
            }

            // Section Header: BASED ON YOUR MOOD
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    // Shard accent indicator
                    Canvas(modifier = Modifier.size(width = 12.dp, height = 24.dp)) {
                        drawPath(
                            path = Path().apply {
                                moveTo(0f, 0f)
                                lineTo(size.width, size.height * 0.4f)
                                lineTo(size.width * 0.5f, size.height)
                                close()
                            },
                            color = CyberPrimary
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "BASED ON YOUR MOOD",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontFamily = TechFontFamily,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.5.sp
                        ),
                        color = CyberPrimaryBright
                    )
                }
            }

            // Grid of Movies based on mood
            item {
                val secondaryMovies = SampleData.sampleMovies.drop(1)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    secondaryMovies.forEach { movie ->
                        MovieGridCard(
                            movie = movie,
                            onMovieClick = { onMovieClick(movie) },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun HeroMovieCard(
    movie: Movie,
    onMovieClick: () -> Unit
) {
    CyberGlassCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onMovieClick() },
        shape = ChamferedShape(topRightCut = 24.dp, bottomLeftCut = 24.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(movie.posterColor),
                            Color(0xFF04191A),
                            CyberBackground
                        )
                    )
                )
                .padding(20.dp)
        ) {
            Column {
                // Badge 98% Match
                CyberBadge(
                    text = "${movie.matchPercentage}% MOOD MATCH",
                    icon = Icons.Default.Mood
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Movie Title
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.displayMedium.copy(
                        fontFamily = TechFontFamily,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp
                    ),
                    color = CyberPrimaryBright
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Genre Tags
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    movie.genres.forEach { genre ->
                        CyberChip(text = genre)
                    }
                    CyberChip(text = movie.year)
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Synopsis
                Text(
                    text = movie.synopsis,
                    style = MaterialTheme.typography.bodyMedium,
                    color = CyberOnSurfaceVariant,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Watch Trailer Button
                CyberButton(
                    text = "WATCH TRAILER",
                    onClick = onMovieClick,
                    icon = Icons.Default.PlayArrow,
                    modifier = Modifier.fillMaxWidth(0.65f)
                )
            }
        }
    }
}

@Composable
private fun MovieGridCard(
    movie: Movie,
    onMovieClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    CyberGlassCard(
        modifier = modifier.clickable { onMovieClick() },
        shape = ChamferedShape(topRightCut = 16.dp, bottomLeftCut = 12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(movie.posterColor),
                            Color(0xFF031A1B)
                        )
                    )
                )
        ) {
            // Poster Placeholder with Badge
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(0.75f)
                    .background(Color(0xFF072124))
            ) {
                // Cyberpunk Poster Pattern Canvas
                Canvas(modifier = Modifier.fillMaxSize()) {
                    drawPath(
                        path = Path().apply {
                            moveTo(size.width * 0.2f, size.height)
                            lineTo(size.width * 0.8f, 0f)
                            lineTo(size.width, 0f)
                            lineTo(size.width * 0.4f, size.height)
                            close()
                        },
                        color = Color(0x3346E5E5)
                    )
                }

                // Match Badge top right
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                ) {
                    CyberBadge(text = "${movie.matchPercentage}%")
                }
            }

            // Card Body
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontFamily = TechFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    ),
                    color = CyberPrimaryBright,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = movie.synopsis,
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 11.sp),
                    color = CyberOnSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = movie.duration,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontFamily = TechFontFamily,
                            fontWeight = FontWeight.Bold
                        ),
                        color = CyberOnSurface
                    )

                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape)
                            .border(1.dp, CyberPrimary, CircleShape)
                            .clickable { onMovieClick() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add",
                            tint = CyberPrimary,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }
    }
}
