package com.project.pelisense.ui.components.questionnaire

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brightness2
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.pelisense.domain.data.EnergyLevel
import com.project.pelisense.domain.data.Tone
import com.project.pelisense.ui.events.QuestionnaireEvent
import com.project.pelisense.ui.screens.ToneEnergyCard
import com.project.pelisense.ui.theme.CyberOnSurface
import com.project.pelisense.ui.theme.CyberOnSurfaceVariant
import com.project.pelisense.ui.theme.CyberPrimaryBright
import com.project.pelisense.ui.theme.TechFontFamily


@Composable
 fun ToneAndEnergySection(
    selectedTone: Tone?,
    selectedEnergy: EnergyLevel?,
    onEvent: (QuestionnaireEvent) -> Unit
) {

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
            isSelected = selectedTone == Tone.POSITIVE,
            onClick = { onEvent(QuestionnaireEvent.ChangeTone(Tone.POSITIVE)) },
            modifier = Modifier.weight(1f)
        )
        ToneEnergyCard(
            title = "Oscuro / Pesado",
            icon = Icons.Default.Brightness2,
            isSelected = selectedTone  == Tone.DARK,
            onClick = { onEvent(QuestionnaireEvent.ChangeTone(Tone.DARK)) },
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
            isSelected = selectedEnergy == EnergyLevel.CALM,
            onClick = { onEvent(QuestionnaireEvent.ChangeEnergyLevel(EnergyLevel.CALM)) },
            modifier = Modifier.weight(1f)
        )
        ToneEnergyCard(
            title = "Intenso / Enérgico",
            icon = Icons.Default.Speed,
            isSelected = selectedEnergy == EnergyLevel.INTENSE,
            onClick = { onEvent(QuestionnaireEvent.ChangeEnergyLevel(EnergyLevel.INTENSE)) },
            modifier = Modifier.weight(1f)
        )
    }
}
