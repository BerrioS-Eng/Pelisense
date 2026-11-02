package com.project.pelisense.global_states

import com.project.pelisense.domain.data.UserData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataStore @Inject constructor() {
    private val _state = MutableStateFlow<UserData?>(null)
    val state = _state.asStateFlow()

    fun updateUserData(userData: UserData) {
        _state.value = userData
    }

    fun removeUserData() {
        _state.value = null
    }
}