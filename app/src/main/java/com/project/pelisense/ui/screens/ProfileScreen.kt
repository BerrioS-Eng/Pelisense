package com.project.pelisense.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.project.pelisense.ui.components.CyberBottomBar
import com.project.pelisense.ui.components.CyberTab
import com.project.pelisense.ui.components.CyberTopBar
import com.project.pelisense.ui.components.profile.AccountInfoCard
import com.project.pelisense.ui.components.profile.HistoryLogCard
import com.project.pelisense.ui.components.profile.ProfileHandler
import com.project.pelisense.ui.components.profile.SecurityCard
import com.project.pelisense.ui.theme.CyberBackground
import com.project.pelisense.ui.theme.CyberPrimary
import com.project.pelisense.ui.ui_states.ProfileUIState

@Composable
fun ProfileScreen(
    onTabSelected: (CyberTab) -> Unit, onSignOutClick: () -> Unit, state: ProfileUIState
) {
    val user = state.userData!!

    Scaffold(
        topBar = {
        CyberTopBar(
            title = "PELISENSE", actions = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Settings",
                        tint = CyberPrimary
                    )
                }
            })
    }, bottomBar = {
        CyberBottomBar(
            currentTab = CyberTab.PROFILE, onTabSelected = onTabSelected
        )
    }, containerColor = CyberBackground
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item { ProfileHandler(user, onSignOutClick) }
            item { AccountInfoCard(user) }
            item { SecurityCard(user) }
            item { HistoryLogCard(user) }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}


