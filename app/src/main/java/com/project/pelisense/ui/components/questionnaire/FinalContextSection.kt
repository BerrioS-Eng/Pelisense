package com.project.pelisense.ui.components.questionnaire

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.pelisense.ui.components.CyberChip
import com.project.pelisense.ui.events.QuestionnaireEvent
import com.project.pelisense.ui.theme.CyberOnSurfaceVariant
import com.project.pelisense.ui.theme.CyberPrimaryBright
import com.project.pelisense.ui.theme.TechFontFamily


@Preview
@Composable
private fun PreviewFinalContextSecction() {
    FinalContextSection(false, false, false, {})
}

@Composable
fun FinalContextSection(
    toViewAccompanied: Boolean?,
    toImproveMood: Boolean?,
    toThink: Boolean?,
    onEvent: (QuestionnaireEvent) -> Unit = {}
) {
    Column {

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
            color = CyberOnSurfaceVariant,
            modifier = Modifier.padding(vertical = 8.dp)

        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)

        ) {
            CyberChip(
                text = "Solo",
                isSelected = toViewAccompanied != null && !toViewAccompanied,
                onClick = { onEvent(QuestionnaireEvent.ChangeToViewAccompanied(false)) }
            )
            CyberChip(
                text = "Acompañado",
                isSelected = toViewAccompanied != null && toViewAccompanied,
                onClick = { onEvent(QuestionnaireEvent.ChangeToViewAccompanied(true)) }
            )
        }

        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "¿Quieres mejorar el tu animo o acompañarlo?",
            style = MaterialTheme.typography.bodyMedium,
            color = CyberOnSurfaceVariant,
            modifier = Modifier.padding(vertical = 8.dp)

        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            CyberChip(
                text = "Mejorar animo",
                isSelected = toImproveMood != null && toImproveMood,
                onClick = { onEvent(QuestionnaireEvent.ChangeToImproveMood(true)) }
            )
            CyberChip(
                text = "Acompañarlo",
                isSelected = toImproveMood != null && !toImproveMood,
                onClick = { onEvent(QuestionnaireEvent.ChangeToImproveMood(false)) }
            )
        }

        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "¿Quieres pensar o desconectarte?",
            style = MaterialTheme.typography.bodyMedium,
            color = CyberOnSurfaceVariant,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            CyberChip(
                text = "Pensar",
                isSelected = toThink != null && toThink,
                onClick = { onEvent(QuestionnaireEvent.ChangeForThinking(true)) }
            )
            CyberChip(
                text = "Desconectar",
                isSelected = toThink != null && !toThink,
                onClick = { onEvent(QuestionnaireEvent.ChangeForThinking(false)) }
            )
        }

    }


}

@Composable
fun ColumnScope.SelectableChips(
    title: String,
    values: List<String>,
    selected: String?,
    onSelect: (String) -> Unit
) {
    Text(
        text = title,
        style = MaterialTheme.typography.bodyMedium,
        color = CyberOnSurfaceVariant
    )

    Spacer(modifier = Modifier.height(16.dp))

    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        values.map { value ->
            CyberChip(
                value,
                isSelected = value == selected,
                onClick = { onSelect(value) }
            )

        }
    }
}

@Preview
@Composable
private fun PreviewSelectableChips() {
    val values = listOf("value 1", "value 2", "value 3")
    var selected: String? by remember { mutableStateOf(null) }
    val onSelect: (String) -> Unit = { selected = it }
    Column {

        SelectableChips(
            "Selecciona una",
            values, selected, onSelect
        )
    }

}
