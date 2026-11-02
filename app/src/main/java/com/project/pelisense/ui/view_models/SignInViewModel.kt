package com.project.pelisense.ui.view_models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.pelisense.global_states.UserDataStore
import com.project.pelisense.ui.ui_states.SignInState
import com.project.pelisense.usecases.auth.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val userStore: UserDataStore
) : ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()


    fun signIn(successCallback: () -> Unit) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            try {
                val user = signInUseCase.execute()

                Log.i("SIGN IN VIEWMODEL", "user: $user")
                _state.update {
                    it.copy(
                        isSingInSuccessful = true,
                        signInError = null
                    )
                }
                userStore.updateUserData(userData = user)
                successCallback()
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isSingInSuccessful = false,
                        signInError = e.message
                    )
                }

            } finally {
                _state.update { it.copy(isLoading = false) }
            }
        }
    }

}
