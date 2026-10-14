package com.project.pelisense.infrasctucture.firebase

import android.content.Context
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.project.pelisense.R
import com.project.pelisense.domain.AuthenticationPort
import com.project.pelisense.domain.data.UserData
import com.project.pelisense.domain.exceptions.AuthenticationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.time.Instant
import java.time.LocalDateTime
import java.util.TimeZone
import kotlin.coroutines.cancellation.CancellationException

class GoogleAuthClient(private val context: Context) : AuthenticationPort {

    private val auth = FirebaseAuth.getInstance()
    private val credentialManager = CredentialManager.create(context)


    override suspend fun signIn(): UserData {
        try {
            val googleIdOption = GetGoogleIdOption.Builder()
                .setFilterByAuthorizedAccounts(false)
                .setServerClientId(context.getString(R.string.web_client_id))
                .build()

            val request = GetCredentialRequest.Builder()
                .addCredentialOption(googleIdOption)
                .build()

            val response = credentialManager.getCredential(context, request)
            val credential = response.credential

            if (credential is CustomCredential &&
                credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
            ) {
                val idToken = GoogleIdTokenCredential.createFrom(credential.data).idToken
                val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)
                val firebaseUser = auth.signInWithCredential(firebaseCredential).await().user
                    ?: throw AuthenticationException("User is  null")

                return buildUserDataFromFirebaseUser(firebaseUser)
            }
            throw AuthenticationException("Unexpected credential type")
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException || e is AuthenticationException) throw e
            throw AuthenticationException("Sign in failed")
        }
    }

    private fun buildUserDataFromFirebaseUser(user: FirebaseUser): UserData = UserData(
        name = user.displayName ?: "No name",
        email = user.email ?: "No email",
        uid = user.uid,
        provider = "Google",
        isVerified = user.isEmailVerified,
        isAnonymous = user.isAnonymous,
        accountCreated = getLocalDateTimeFromLong(user.metadata?.creationTimestamp),
        lastSignIn = getLocalDateTimeFromLong(user.metadata?.lastSignInTimestamp),
        profilePictureUrl = user.photoUrl.toString()
    )

    private fun getLocalDateTimeFromLong(milli: Long?): LocalDateTime? {
        if (milli == null) return null;
        return LocalDateTime.ofInstant(
            Instant.ofEpochMilli(milli),
            TimeZone.getDefault().toZoneId()
        )
    }

    override suspend fun signOut() {
        auth.signOut()
        credentialManager.clearCredentialState(ClearCredentialStateRequest())
    }
}