package com.project.pelisense.usecases.auth

import com.project.pelisense.domain.AuthenticationPort
import javax.inject.Inject

class SignOutUseCase @Inject constructor(private val authenticationPort: AuthenticationPort)  {
    suspend fun execute() {
        authenticationPort.signOut()
    }
}