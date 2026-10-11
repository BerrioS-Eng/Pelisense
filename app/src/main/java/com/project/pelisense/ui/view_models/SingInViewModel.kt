package com.project.pelisense.ui.view_models

import androidx.lifecycle.ViewModel
import com.project.pelisense.ui.ui_states.SignInState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SingInViewModel : ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

}
