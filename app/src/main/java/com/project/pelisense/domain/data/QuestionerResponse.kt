package com.project.pelisense.domain.data

enum class Feelings {
    HAPPY,
    SAD,
    STRESSED,
    BORED,
    ANXIOUS,
    ANGRY,
    NOSTALGIC,
    ROMANTIC,
    EXCITED,
    RELAXED,
    LONELY,
    ENERGETIC
}

enum class Tone {
    POSITIVE,
    DARK
}

enum class EnergyLevel {
    CALM,
    INTENSE
}

data class QuestionerResponse(
    val feelings: List<Feelings> = listOf(),
    val tone: Tone? = null,
    val energyLevel: EnergyLevel? = null,
    val toViewAccompanied: Boolean? = null,
    val improveMood: Boolean? = null,
    val forThinking: Boolean? = null
)