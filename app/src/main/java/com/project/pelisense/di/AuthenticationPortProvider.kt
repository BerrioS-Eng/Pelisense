package com.project.pelisense.di

import android.content.Context
import com.project.pelisense.domain.AuthenticationPort
import com.project.pelisense.infrasctucture.firebase.GoogleAuthClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AuthenticationPortProvider {

    @Provides
    @Singleton
    fun authenticationPortProvider(@ApplicationContext context: Context): AuthenticationPort {
        return GoogleAuthClient(context)
    }
}