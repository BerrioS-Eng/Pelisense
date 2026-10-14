package com.project.pelisense.ui.ui_states

import com.project.pelisense.domain.data.UserData

data class ProfileUIState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: UserData? = null
)
