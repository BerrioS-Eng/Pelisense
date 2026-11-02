package com.project.pelisense.ui.view_models

import androidx.lifecycle.ViewModel
import com.project.pelisense.domain.data.EnergyLevel
import com.project.pelisense.domain.data.Feelings
import com.project.pelisense.domain.data.QuestionerResponse
import com.project.pelisense.domain.data.Tone
import com.project.pelisense.ui.events.QuestionnaireEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.collections.plus

class QuestionnaireViewModel : ViewModel() {

    private val _state = MutableStateFlow(QuestionerResponse())
    val state = _state.asStateFlow()

    fun onQuestionnaireEvent(event: QuestionnaireEvent) {
        when (event) {
            is QuestionnaireEvent.AddFeeling -> addFeeling(event.feeling)
            is QuestionnaireEvent.RemoveFeeling -> removeFeeling(event.feeling)
            is QuestionnaireEvent.ChangeEnergyLevel -> changeEnergyLevel(event.energyLevel)
            is QuestionnaireEvent.ChangeForThinking -> changeForThinking(event.forThinking)
            is QuestionnaireEvent.ChangeToImproveMood -> changeToImproveMood(event.toImproveMood)
            is QuestionnaireEvent.ChangeToViewAccompanied -> changeToViewAccompanied(event.toViewAccompanied)
            is QuestionnaireEvent.ChangeTone -> changeTone(event.tone)
        }

    }

    private fun addFeeling(feeling: Feelings) =
        _state.update { it.copy(feelings = it.feelings + feeling) }

    private fun removeFeeling(feeling: Feelings) =
        _state.update { it.copy(feelings = it.feelings - feeling) }

    private fun changeEnergyLevel(energyLevel: EnergyLevel) =
        _state.update { it.copy(energyLevel = energyLevel) }

    private fun changeForThinking(forThinking: Boolean) =
        _state.update { it.copy(forThinking = forThinking) }

    private fun changeToImproveMood(toImproveMood: Boolean) =
        _state.update { it.copy(improveMood = toImproveMood) }

    private fun changeToViewAccompanied(toViewAccompanied: Boolean) =
        _state.update { it.copy(toViewAccompanied = toViewAccompanied) }

    private fun changeTone(tone: Tone) = _state.update { it.copy(tone = tone) }
}