package com.project.pelisense.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brightness2
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.pelisense.data.SampleData
import com.project.pelisense.ui.components.CyberButton
import com.project.pelisense.ui.components.CyberChip
import com.project.pelisense.ui.components.CyberTopBar
import com.project.pelisense.ui.components.LaserDivider
import com.project.pelisense.ui.theme.ChamferedShape
import com.project.pelisense.ui.theme.CyberBackground
import com.project.pelisense.ui.theme.CyberGlassBg
import com.project.pelisense.ui.theme.CyberGlassBorder
import com.project.pelisense.ui.theme.CyberOnSurface
import com.project.pelisense.ui.theme.CyberOnSurfaceVariant
import com.project.pelisense.ui.theme.CyberOutlineVariant
import com.project.pelisense.ui.theme.CyberPrimary
import com.project.pelisense.ui.theme.CyberPrimaryBright
import com.project.pelisense.ui.theme.TechFontFamily

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun QuestionnaireScreen(
    onGenerateClick: () -> Unit,
    onBackClick: () -> Unit
) {
    val selectedMoods = remember { mutableStateListOf("Happy") }
    var selectedTone by remember { mutableStateOf("Positivo / Alegre") }
    var selectedEnergy by remember { mutableStateOf("Intenso / Enérgico") }
    val selectedContexts = remember { mutableStateListOf("Solo", "Mejorar ánimo") }

    Scaffold(
        topBar = {
            CyberTopBar(
                title = "PELISENSE",
                onBackClick = onBackClick
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(CyberBackground)
                    .navigationBarsPadding()
                    .padding(16.dp)
            ) {
                CyberButton(
                    text = "GENERAR RECOMENDACIONES",
                    onClick = onGenerateClick,
                    icon = Icons.Default.Movie,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        containerColor = CyberBackground
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 12.dp)
        ) {
            // SECTION 1: ¿Cómo te sientes?
            Text(
                text = "¿Cómo te sientes?",
                style = MaterialTheme.typography.displayMedium.copy(
                    fontFamily = TechFontFamily,
                    fontSize = 22.sp,
                    letterSpacing = 1.sp
                ),
                color = CyberPrimaryBright
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Selecciona el estado de ánimo que más te represente hoy.",
                style = MaterialTheme.typography.bodyMedium,
                color = CyberOnSurfaceVariant
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 12 Mood Grid
            val moods = SampleData.moodOptions
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                for (row in moods.chunked(3)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        for ((name, emoji) in row) {
                            val isSelected = selectedMoods.contains(name)
                            MoodCard(
                                name = name,
                                emoji = emoji,
                                isSelected = isSelected,
                                onClick = {
                                    if (isSelected) selectedMoods.remove(name) else selectedMoods.add(name)
                                },
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            LaserDivider()
            Spacer(modifier = Modifier.height(24.dp))

            // SECTION 2: Tono y Energía
            Text(
                text = "Tono y Energía",
                style = MaterialTheme.typography.displayMedium.copy(
                    fontFamily = TechFontFamily,
                    fontSize = 22.sp,
                    letterSpacing = 1.sp
                ),
                color = CyberPrimaryBright
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Ajusta las preferencias para refinar la búsqueda.",
                style = MaterialTheme.typography.bodyMedium,
                color = CyberOnSurfaceVariant
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Tono de la película
            Text(
                text = "Tono de la película",
                style = MaterialTheme.typography.labelMedium.copy(
                    fontFamily = TechFontFamily,
                    fontWeight = FontWeight.Bold
                ),
                color = CyberOnSurface
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ToneEnergyCard(
                    title = "Positivo / Alegre",
                    icon = Icons.Default.WbSunny,
                    isSelected = selectedTone == "Positivo / Alegre",
                    onClick = { selectedTone = "Positivo / Alegre" },
                    modifier = Modifier.weight(1f)
                )
                ToneEnergyCard(
                    title = "Oscuro / Pesado",
                    icon = Icons.Default.Brightness2,
                    isSelected = selectedTone == "Oscuro / Pesado",
                    onClick = { selectedTone = "Oscuro / Pesado" },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Nivel de Energía
            Text(
                text = "Nivel de Energía",
                style = MaterialTheme.typography.labelMedium.copy(
                    fontFamily = TechFontFamily,
                    fontWeight = FontWeight.Bold
                ),
                color = CyberOnSurface
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ToneEnergyCard(
                    title = "Calmado / Lento",
                    icon = Icons.Default.WaterDrop,
                    isSelected = selectedEnergy == "Calmado / Lento",
                    onClick = { selectedEnergy = "Calmado / Lento" },
                    modifier = Modifier.weight(1f)
                )
                ToneEnergyCard(
                    title = "Intenso / Enérgico",
                    icon = Icons.Default.Speed,
                    isSelected = selectedEnergy == "Intenso / Enérgico",
                    onClick = { selectedEnergy = "Intenso / Enérgico" },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
            LaserDivider()
            Spacer(modifier = Modifier.height(24.dp))

            // SECTION 3: Contexto Final
            Text(
                text = "Contexto Final",
                style = MaterialTheme.typography.displayMedium.copy(
                    fontFamily = TechFontFamily,
                    fontSize = 22.sp,
                    letterSpacing = 1.sp
                ),
                color = CyberPrimaryBright
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "¿Cómo planeas verla?",
                style = MaterialTheme.typography.bodyMedium,
                color = CyberOnSurfaceVariant
            )

            Spacer(modifier = Modifier.height(16.dp))

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                SampleData.contextPills.forEach { pill ->
                    val isSel = selectedContexts.contains(pill)
                    CyberChip(
                        text = pill,
                        isSelected = isSel,
                        onClick = {
                            if (isSel) selectedContexts.remove(pill) else selectedContexts.add(pill)
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
private fun MoodCard(
    name: String,
    emoji: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val shape = ChamferedShape(topRightCut = 8.dp, bottomLeftCut = 8.dp)
    val border = if (isSelected) CyberPrimary else CyberOutlineVariant
    val bg = if (isSelected) Color(0xFF0F3E3E) else CyberGlassBg

    Box(
        modifier = modifier
            .height(72.dp)
            .clip(shape)
            .background(bg)
            .border(1.dp, border, shape)
            .clickable { onClick() }
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = emoji, fontSize = 22.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = name,
                style = MaterialTheme.typography.labelSmall.copy(
                    fontFamily = TechFontFamily,
                    fontWeight = FontWeight.Bold
                ),
                color = if (isSelected) CyberPrimary else CyberOnSurface
            )
        }
    }
}

@Composable
private fun ToneEnergyCard(
    title: String,
    icon: ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val shape = ChamferedShape(topRightCut = 10.dp)
    val border = if (isSelected) CyberPrimary else CyberOutlineVariant
    val bg = if (isSelected) Color(0xFF0F3E3E) else CyberGlassBg

    Box(
        modifier = modifier
            .height(84.dp)
            .clip(shape)
            .background(bg)
            .border(1.dp, border, shape)
            .clickable { onClick() }
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (isSelected) CyberPrimary else CyberOnSurfaceVariant,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.labelSmall.copy(
                    fontFamily = TechFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp
                ),
                color = if (isSelected) CyberPrimary else CyberOnSurface
            )
        }
    }
}
