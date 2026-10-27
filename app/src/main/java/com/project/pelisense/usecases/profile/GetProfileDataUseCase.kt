package com.project.pelisense.usecases.profile

import android.content.Context
import com.project.pelisense.R
import com.project.pelisense.domain.AuthenticationPort
import com.project.pelisense.domain.data.UserData
import com.project.pelisense.domain.exceptions.AuthenticationException
import com.project.pelisense.global_states.UserDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject

class GetProfileDataUseCase @Inject constructor(
    @ApplicationContext private val context: Context,
    private val userDataStore: UserDataStore
) {

    fun execute(): UserData {
        userDataStore.state.value?.let {
            return it
        }
        throw AuthenticationException(context.getString(R.string.not_active_session_error))
    }
}