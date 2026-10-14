package com.project.pelisense.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.pelisense.ui.components.CyberButton
import com.project.pelisense.ui.components.CyberGlassCard
import com.project.pelisense.ui.components.PelisenseLogoIcon
import com.project.pelisense.ui.theme.CyberBackground
import com.project.pelisense.ui.theme.CyberPrimaryBright
import com.project.pelisense.ui.theme.TechFontFamily
import com.project.pelisense.ui.theme.cyberCardShape

import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.project.pelisense.R
import com.project.pelisense.ui.theme.PelisenseTheme
import com.project.pelisense.ui.ui_states.SignInState

@Composable
fun WelcomeScreen(
    onContinueClick: () -> Unit,
    state: SignInState
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(CyberBackground)
            .systemBarsPadding(),
        contentAlignment = Alignment.Center
    ) {
        CyberGlassCard(
            modifier = Modifier
                .fillMaxWidth(0.88f)
                .padding(24.dp),
            shape = cyberCardShape(20.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 36.dp, horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Pelisense Logo Icon
                PelisenseLogoIcon(size = 100.dp)

                Spacer(modifier = Modifier.height(32.dp))

                // Title BIENVENIDO A PELISENSE
                Text(
                    text = stringResource(R.string.welcome_to_pelisense),
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontFamily = TechFontFamily,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp,
                        lineHeight = 28.sp
                    ),
                    color = CyberPrimaryBright,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(36.dp))

                // Continuar con Google Button
                CyberButton(
                    text = stringResource(R.string.continue_with_google),
                    onClick = onContinueClick,
                    icon = Icons.Default.AccountCircle,
                    modifier = Modifier.fillMaxWidth()
                )
                if (state.isLoading) {
                    Spacer(modifier = Modifier.height(36.dp))
                    CircularProgressIndicator()
                }

                state.signInError?.let {
                    Spacer(modifier = Modifier.height(36.dp))
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontFamily = TechFontFamily,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.error
                        ),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    PelisenseTheme {
        WelcomeScreen(
            onContinueClick = {},
            state = SignInState()
        )
    }
}


@Preview(showBackground = true)
@Composable
fun WelcomeScreenLoadingPreview() {
    PelisenseTheme {
        WelcomeScreen(
            onContinueClick = {},
            state = SignInState(isLoading = true)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenErrorPreview() {
    PelisenseTheme {
        WelcomeScreen(
            onContinueClick = {},
            state = SignInState(signInError = "Couldn't sign in")
        )
    }
}