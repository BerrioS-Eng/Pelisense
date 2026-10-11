package com.project.pelisense.domain

import com.project.pelisense.domain.data.UserData
import kotlinx.coroutines.flow.Flow

interface AuthenticationPort {

    suspend fun signIn(): Flow<UserData>

    suspend fun signOut()
}