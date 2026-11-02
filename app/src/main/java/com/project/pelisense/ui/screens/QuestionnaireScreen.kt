package com.project.pelisense.ui.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.pelisense.domain.data.QuestionerResponse
import com.project.pelisense.ui.components.CyberButton
import com.project.pelisense.ui.components.CyberTopBar
import com.project.pelisense.ui.components.LaserDivider
import com.project.pelisense.ui.components.questionnaire.FellingSection
import com.project.pelisense.ui.components.questionnaire.FinalContextSection
import com.project.pelisense.ui.components.questionnaire.ToneAndEnergySection
import com.project.pelisense.ui.events.QuestionnaireEvent
import com.project.pelisense.ui.theme.ChamferedShape
import com.project.pelisense.ui.theme.CyberBackground
import com.project.pelisense.ui.theme.CyberGlassBg
import com.project.pelisense.ui.theme.CyberOnSurface
import com.project.pelisense.ui.theme.CyberOnSurfaceVariant
import com.project.pelisense.ui.theme.CyberOutlineVariant
import com.project.pelisense.ui.theme.CyberPrimary
import com.project.pelisense.ui.theme.TechFontFamily

@Composable
fun QuestionnaireScreen(
    onGenerateClick: () -> Unit,
    onBackClick: () -> Unit,
    state: QuestionerResponse,
    onEvent: (QuestionnaireEvent) -> Unit
) {

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
            FellingSection(state.feelings, onEvent)

            Spacer(modifier = Modifier.height(24.dp))
            LaserDivider()
            Spacer(modifier = Modifier.height(24.dp))

            // SECTION 2: Tono y Energía
            ToneAndEnergySection(state.tone, state.energyLevel, onEvent)

            Spacer(modifier = Modifier.height(24.dp))
            LaserDivider()
            Spacer(modifier = Modifier.height(24.dp))

            // SECTION 3: Contexto Final
            FinalContextSection(
                toViewAccompanied = state.toViewAccompanied,
                toImproveMood = state.improveMood,
                toThink = state.forThinking,
                onEvent = onEvent
            )

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Preview
@Composable
private fun PreviewQuestionarieScreen() {
    QuestionnaireScreen({}, {}, QuestionerResponse()) {}
}

@Composable
fun ToneEnergyCard(
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
