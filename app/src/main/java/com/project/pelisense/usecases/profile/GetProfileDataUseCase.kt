package com.project.pelisense.usecases.profile

import android.content.Context
import com.project.pelisense.R
import com.project.pelisense.domain.AuthenticationPort
import com.project.pelisense.domain.data.UserData
import com.project.pelisense.domain.exceptions.AuthenticationException
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject

class GetProfileDataUseCase @Inject constructor(
    private val authenticationManager: AuthenticationPort,
    @ApplicationContext private val context: Context
) {

    suspend fun execute(): UserData {
        authenticationManager.getCurrentUser()?.let { userData -> return userData }
        throw AuthenticationException(context.getString(R.string.not_active_session_error))
    }
}