package com.project.pelisense.ui.ui_states

data class SignInState(
    val isLoading: Boolean = false,
    val isSingInSuccessful: Boolean = false,
    val signInError: String? = null
)