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
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.project.pelisense.ui.theme.CyberGlassBorder
import com.project.pelisense.ui.theme.CyberOnSurface
import com.project.pelisense.ui.theme.CyberOnSurfaceVariant
import com.project.pelisense.ui.theme.CyberOutlineVariant
import com.project.pelisense.ui.theme.CyberPrimary
import com.project.pelisense.ui.theme.CyberPrimaryBright
import com.project.pelisense.ui.theme.TechFontFamily

@Composable
fun MyListScreen(
    onMovieClick: (Movie) -> Unit,
    onTabSelected: (CyberTab) -> Unit,
    onBackClick: () -> Unit
) {
    val savedMovies = SampleData.sampleMovies.filter { it.isSaved }

    Scaffold(
        topBar = {
            CyberTopBar(
                title = "MI LISTA",
                onBackClick = onBackClick,
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings",
                            tint = CyberPrimary
                        )
                    }
                }
            )
        },
        bottomBar = {
            CyberBottomBar(
                currentTab = CyberTab.HISTORY,
                onTabSelected = onTabSelected
            )
        },
        containerColor = CyberBackground
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // SUBHEADER: Count & Filter
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${savedMovies.size} Películas Guardadas",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontFamily = TechFontFamily,
                            fontWeight = FontWeight.Medium
                        ),
                        color = CyberOnSurfaceVariant
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clickable { }
                            .padding(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.FilterList,
                            contentDescription = "Filter",
                            tint = CyberPrimary,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "FILTRAR",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontFamily = TechFontFamily,
                                fontWeight = FontWeight.Bold
                            ),
                            color = CyberPrimary
                        )
                    }
                }
            }

            // MOVIE CARDS LIST
            items(savedMovies) { movie ->
                SavedMovieCard(
                    movie = movie,
                    onMovieClick = { onMovieClick(movie) }
                )
            }
        }
    }
}

@Composable
private fun SavedMovieCard(
    movie: Movie,
    onMovieClick: () -> Unit
) {
    CyberGlassCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onMovieClick() },
        shape = ChamferedShape(topRightCut = 16.dp, bottomLeftCut = 12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .background(Color(0xFF041C1E))
        ) {
            // Poster Left
            Box(
                modifier = Modifier
                    .width(120.dp)
                    .fillMaxSize()
                    .background(Color(movie.posterColor))
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    drawPath(
                        path = Path().apply {
                            moveTo(0f, size.height * 0.3f)
                            lineTo(size.width, size.height)
                            lineTo(0f, size.height)
                            close()
                        },
                        color = Color(0x3346E5E5)
                    )
                }

                // Rating Badge top left
                Box(modifier = Modifier.padding(8.dp)) {
                    CyberBadge(
                        text = "⭐ ${movie.rating}",
                        icon = Icons.Default.Star
                    )
                }
            }

            // Content Right
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .padding(14.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        movie.genres.take(2).forEach { genre ->
                            CyberChip(text = genre)
                        }
                        CyberChip(text = movie.year)
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = movie.title,
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontFamily = TechFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        ),
                        color = CyberPrimaryBright,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                // Action Buttons: DETALLES & Bookmark
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CyberButton(
                        text = "DETALLES",
                        onClick = onMovieClick,
                        icon = Icons.Default.Info,
                        modifier = Modifier.height(40.dp)
                    )

                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(
                                color = Color(0xFF082E30),
                                shape = ChamferedShape(topRightCut = 6.dp)
                            )
                            .border(
                                width = 1.dp,
                                color = CyberGlassBorder,
                                shape = ChamferedShape(topRightCut = 6.dp)
                            )
                            .clickable { },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Bookmark,
                            contentDescription = "Saved",
                            tint = CyberPrimary,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }
}
