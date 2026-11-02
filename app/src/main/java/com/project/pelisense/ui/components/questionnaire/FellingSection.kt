package com.project.pelisense.ui.components.questionnaire

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.pelisense.domain.data.Feelings
import com.project.pelisense.ui.events.QuestionnaireEvent
import com.project.pelisense.ui.theme.ChamferedShape
import com.project.pelisense.ui.theme.CyberBackground
import com.project.pelisense.ui.theme.CyberGlassBg
import com.project.pelisense.ui.theme.CyberOnSurface
import com.project.pelisense.ui.theme.CyberOnSurfaceVariant
import com.project.pelisense.ui.theme.CyberOutlineVariant
import com.project.pelisense.ui.theme.CyberPrimary
import com.project.pelisense.ui.theme.CyberPrimaryBright
import com.project.pelisense.ui.theme.PelisenseTheme
import com.project.pelisense.ui.theme.TechFontFamily
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FellingSection(selectedFeelings: List<Feelings>, onEvent: (QuestionnaireEvent) -> Unit) {
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
    // create a 3x3 array
    val moods = Feelings.entries
        .chunked(3)
        .map { it.toTypedArray() }
        .toTypedArray()

    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {

        for (row in moods) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                row.forEach { feeling ->
                    val isSelected = selectedFeelings.contains(feeling)
                    MoodCard(
                        name = feeling.toString(),
                        emoji = feeling.emoji,
                        isSelected = isSelected,
                        onClick = {
                            if (isSelected) {
                                onEvent(QuestionnaireEvent.RemoveFeeling(feeling))
                            } else {
                                onEvent(QuestionnaireEvent.AddFeeling(feeling))
                            }
                        },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
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

@Preview(showBackground = true)
@Composable
fun FellingSectionPreview() {
    PelisenseTheme {
        Column(
            modifier = Modifier
                .background(CyberBackground)
                .padding(16.dp)
        ) {
            FellingSection(
                selectedFeelings = listOf(Feelings.HAPPY, Feelings.ROMANTIC),
                onEvent = {}
            )
        }
    }
}

