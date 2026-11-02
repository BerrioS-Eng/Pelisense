package com.project.pelisense.ui.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.project.pelisense.ui.ui_states.ProfileUIState
import com.project.pelisense.usecases.auth.SignOutUseCase
import com.project.pelisense.usecases.profile.GetProfileDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getProfileDataUseCase: GetProfileDataUseCase,
    private val singOutUseCase: SignOutUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ProfileUIState())
    val state = _state.asStateFlow()

    init {
        getProfileData()
    }


    fun getProfileData() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            try {
                val userData = getProfileDataUseCase.execute()
                _state.update {
                    it.copy(
                        errorMessage = null,
                        isLoading = false,
                        userData = userData
                    )
                }
            } catch (e: Error) {
                _state.update {
                    it.copy(
                        errorMessage = e.message,
                        isLoading = false,
                        userData = null
                    )
                }
            } finally {
                _state.update { it.copy(isLoading = false) }
            }
        }
    }

    fun singOut() {
        viewModelScope.launch { singOutUseCase.execute() }
    }

}