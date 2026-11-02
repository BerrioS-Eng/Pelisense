package com.project.pelisense.ui.events

import com.project.pelisense.domain.data.EnergyLevel
import com.project.pelisense.domain.data.Feelings
import com.project.pelisense.domain.data.Tone

sealed interface QuestionnaireEvent {

    data class AddFeeling(val feeling: Feelings) : QuestionnaireEvent
    data class RemoveFeeling(val feeling: Feelings) : QuestionnaireEvent
    data class ChangeTone(val tone: Tone) : QuestionnaireEvent
    data class ChangeEnergyLevel(val energyLevel: EnergyLevel) : QuestionnaireEvent
    data class ChangeToViewAccompanied(val toViewAccompanied: Boolean) : QuestionnaireEvent
    data class ChangeToImproveMood(val toImproveMood: Boolean) : QuestionnaireEvent
    data class ChangeForThinking(val forThinking: Boolean) : QuestionnaireEvent

}