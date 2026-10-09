package com.project.pelisense.presentation.login

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.project.pelisense.R
import com.project.pelisense.data.UserData
import kotlinx.coroutines.tasks.await
import java.time.Instant
import java.time.LocalDateTime
import java.util.TimeZone
import kotlin.coroutines.cancellation.CancellationException

class GoogleAuthUiClient(
    private val context: Context,
    private val oneTapClient: SignInClient
) {
    private val auth = Firebase.auth

    suspend fun singIn(): IntentSender? {
        val result = try {
            oneTapClient.beginSignIn(
                buildSingInRequest()
            ).await()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is PendingIntent.CanceledException) throw e
            null
        }
        return result?.pendingIntent?.intentSender
    }

    suspend fun getSignInResultFromIntent(intent: Intent): SignInResult {
        val credential = oneTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)
        return try {
            val user = auth.signInWithCredential(googleCredentials).await().user ?: throw Exception(
                "Invalid login"
            )
            buildSignInResultFromFirebaseUser(user)
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            SignInResult(
                data = null,
                errorMessage = e.message,
            )
        }
    }

    private fun buildSignInResultFromFirebaseUser(user: FirebaseUser): SignInResult = SignInResult(
        getSignedInUser(),
        errorMessage = null,
    )

    private fun getLocalDateTimeFromLong(milli: Long): LocalDateTime = LocalDateTime.ofInstant(
        Instant.ofEpochMilli(milli),
        TimeZone.getDefault().toZoneId()
    )

    suspend fun signOut() {
        try {
            oneTapClient.signOut().await()
            auth.signOut()
        }catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
        }
    }

    fun getSignedInUser(): UserData?  = auth.currentUser?.run {
        UserData(
            email = email!!,
            uid = uid,
            provider = providerId,
            isVerified = isEmailVerified,
            isAnonymous = isAnonymous,
            accountCreated = getLocalDateTimeFromLong(metadata?.creationTimestamp!!),
            lastSignIn = getLocalDateTimeFromLong(metadata?.lastSignInTimestamp!!),
            name = displayName!!,
            profilePictureUrl = photoUrl?.toString()
        )
    }


    private fun buildSingInRequest(): BeginSignInRequest {
        return BeginSignInRequest.Builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(context.getString(R.string.web_client_id))
                    .build()
            )
            .setAutoSelectEnabled(true)
            .build()
    }
}