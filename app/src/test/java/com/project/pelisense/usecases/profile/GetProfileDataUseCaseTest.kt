package com.project.pelisense.usecases.profile

import android.content.Context
import com.project.pelisense.R
import com.project.pelisense.domain.AuthenticationPort
import com.project.pelisense.domain.data.UserData
import com.project.pelisense.domain.exceptions.AuthenticationException
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetProfileDataUseCaseTest {
    private val authenticationPort = mockk<AuthenticationPort>()
    private val context = mockk<Context>()
    private lateinit var useCase: GetProfileDataUseCase

    private val mockedStringError = "mocked string"


    @Before
    fun setup() {
        this.useCase = GetProfileDataUseCase(authenticationPort, context)
        every { context.getString(R.string.not_active_session_error) } returns mockedStringError
    }


    @Test
    fun returnUserDataWhenSuccess() = runTest {
        val userData = UserData(
            name = "name",
            email = "email",
            uid = "uid",
            provider = "provider",
            isVerified = true,
            isAnonymous = false,
            accountCreated = null,
            lastSignIn = null,
            profilePictureUrl = null
        )
        coEvery { authenticationPort.getCurrentUser() } returns userData
        val actual = useCase.execute()
        assertEquals(userData, actual)
    }


    @Test
    fun throwErrorWhenDataIsNull() = runTest {
        coEvery { authenticationPort.getCurrentUser() } returns null
        val actual = runCatching { useCase.execute() }
        assertTrue(actual.exceptionOrNull() is AuthenticationException)
        assertEquals(mockedStringError, actual.exceptionOrNull()?.message)
    }
}