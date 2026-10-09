package com.project.pelisense.data

import java.time.LocalDateTime

data class UserData(
    val name: String,
    val email: String,
    val uid: String,
    val provider: String = "google.com",
    val isVerified: Boolean = true,
    val isAnonymous: Boolean = false,
    val accountCreated: LocalDateTime,
    val lastSignIn: LocalDateTime,
    val profilePictureUrl: String? = null
)
