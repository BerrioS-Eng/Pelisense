package com.project.pelisense.presentation.login

import com.project.pelisense.data.UserData

data class SignInResult(
    val data: UserData?,
    val errorMessage: String?,
)


