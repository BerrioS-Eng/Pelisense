package com.project.pelisense.usecases.auth

import com.project.pelisense.domain.AuthenticationPort
import com.project.pelisense.domain.data.UserData
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class SignInUseCase @Inject constructor(private val authenticationPort: AuthenticationPort) {
    suspend fun execute(): UserData {
        return authenticationPort.signIn()
    }
}