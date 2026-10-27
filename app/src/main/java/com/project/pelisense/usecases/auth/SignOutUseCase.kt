package com.project.pelisense.usecases.auth

import com.project.pelisense.domain.AuthenticationPort
import com.project.pelisense.global_states.UserDataStore
import javax.inject.Inject

class SignOutUseCase @Inject constructor(
    private val authenticationPort: AuthenticationPort,
    private val userDataStore: UserDataStore
)  {
    suspend fun execute() {
        authenticationPort.signOut()
        userDataStore.removeUserData()
    }
}