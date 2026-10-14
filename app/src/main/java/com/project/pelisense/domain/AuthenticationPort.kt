package com.project.pelisense.domain

import com.project.pelisense.domain.data.UserData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

interface AuthenticationPort {
    suspend fun getCurrentUser(): UserData?
    suspend fun signIn(): UserData

    suspend fun signOut()
}