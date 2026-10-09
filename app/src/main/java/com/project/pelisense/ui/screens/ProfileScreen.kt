package com.project.pelisense.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.pelisense.data.SampleData
import com.project.pelisense.ui.components.CyberBadge
import com.project.pelisense.ui.components.CyberBottomBar
import com.project.pelisense.ui.components.CyberButton
import com.project.pelisense.ui.components.CyberGlassCard
import com.project.pelisense.ui.components.CyberOutlinedButton
import com.project.pelisense.ui.components.CyberTab
import com.project.pelisense.ui.components.CyberTopBar
import com.project.pelisense.ui.components.LaserDivider
import com.project.pelisense.ui.components.PelisenseLogoIcon
import com.project.pelisense.ui.theme.ChamferedShape
import com.project.pelisense.ui.theme.CyberBackground
import com.project.pelisense.ui.theme.CyberOnSurface
import com.project.pelisense.ui.theme.CyberOnSurfaceVariant
import com.project.pelisense.ui.theme.CyberPrimary
import com.project.pelisense.ui.theme.CyberPrimaryBright
import com.project.pelisense.ui.theme.TechFontFamily

@Composable
fun ProfileScreen(
    onTabSelected: (CyberTab) -> Unit,
    onSignOutClick: () -> Unit
) {
    val user = SampleData.sampleUser

    Scaffold(
        topBar = {
            CyberTopBar(
                title = "PELISENSE",
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings",
                            tint = CyberPrimary
                        )
                    }
                }
            )
        },
        bottomBar = {
            CyberBottomBar(
                currentTab = CyberTab.PROFILE,
                onTabSelected = onTabSelected
            )
        },
        containerColor = CyberBackground
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // PROFILE HEADER
            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(12.dp))

                    // Double ringed avatar with 'P' logo
                    Box(
                        modifier = Modifier
                            .size(110.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF031E1E))
                            .border(2.dp, CyberPrimary, CircleShape)
                            .padding(6.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        PelisenseLogoIcon(size = 84.dp)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = user.name,
                        style = MaterialTheme.typography.displayMedium.copy(
                            fontFamily = TechFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        ),
                        color = CyberPrimaryBright
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = user.email,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontFamily = TechFontFamily
                        ),
                        color = CyberOnSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // Buttons Row: EDIT PROFILE & SIGN OUT
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        CyberButton(
                            text = "EDIT PROFILE",
                            onClick = { },
                            modifier = Modifier.weight(1f)
                        )
                        CyberOutlinedButton(
                            text = "SIGN OUT",
                            onClick = onSignOutClick,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            // ACCOUNT INFO CARD
            item {
                CyberGlassCard(
                    modifier = Modifier.fillMaxWidth(),
                    shape = ChamferedShape(topRightCut = 16.dp, bottomLeftCut = 16.dp)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Badge,
                                contentDescription = null,
                                tint = CyberPrimary,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Account Info",
                                style = MaterialTheme.typography.headlineMedium.copy(
                                    fontFamily = TechFontFamily,
                                    fontWeight = FontWeight.Bold
                                ),
                                color = CyberPrimaryBright
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                        LaserDivider()
                        Spacer(modifier = Modifier.height(16.dp))

                        // UID
                        Text(
                            text = "Unique ID (UID)",
                            style = MaterialTheme.typography.labelSmall,
                            color = CyberOnSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color(0xFF072425))
                                .padding(horizontal = 12.dp, vertical = 10.dp)
                        ) {
                            Text(
                                text = user.uid,
                                style = MaterialTheme.typography.bodyMedium.copy(fontFamily = TechFontFamily),
                                color = CyberOnSurface
                            )
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        // Provider
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Provider",
                                style = MaterialTheme.typography.bodyMedium,
                                color = CyberOnSurfaceVariant
                            )
                            Text(
                                text = user.provider,
                                style = MaterialTheme.typography.bodyMedium.copy(fontFamily = TechFontFamily),
                                color = CyberPrimaryBright
                            )
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        // Email Status
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Email Status",
                                style = MaterialTheme.typography.bodyMedium,
                                color = CyberOnSurfaceVariant
                            )
                            CyberBadge(
                                text = if (user.isVerified) "Verified" else "Unverified",
                                icon = Icons.Default.CheckCircle
                            )
                        }
                    }
                }
            }

            // SECURITY CARD
            item {
                CyberGlassCard(
                    modifier = Modifier.fillMaxWidth(),
                    shape = ChamferedShape(topRightCut = 16.dp, bottomLeftCut = 16.dp)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Security,
                                contentDescription = null,
                                tint = CyberPrimary,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Security",
                                style = MaterialTheme.typography.headlineMedium.copy(
                                    fontFamily = TechFontFamily,
                                    fontWeight = FontWeight.Bold
                                ),
                                color = CyberPrimaryBright
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                        LaserDivider()
                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = "Account Type",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = CyberOnSurfaceVariant
                                )
                                Text(
                                    text = user.accountType,
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = CyberOnSurface
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Anonymous Session",
                                style = MaterialTheme.typography.bodyMedium,
                                color = CyberOnSurfaceVariant
                            )
                            Text(
                                text = user.isAnonymous.toString().replaceFirstChar { it.uppercase() },
                                style = MaterialTheme.typography.bodyMedium.copy(fontFamily = TechFontFamily),
                                color = CyberOnSurface
                            )
                        }
                    }
                }
            }

            // HISTORY LOG CARD
            item {
                CyberGlassCard(
                    modifier = Modifier.fillMaxWidth(),
                    shape = ChamferedShape(topRightCut = 16.dp, bottomLeftCut = 16.dp)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.History,
                                contentDescription = null,
                                tint = CyberPrimary,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "History Log",
                                style = MaterialTheme.typography.headlineMedium.copy(
                                    fontFamily = TechFontFamily,
                                    fontWeight = FontWeight.Bold
                                ),
                                color = CyberPrimaryBright
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                        LaserDivider()
                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Account Created",
                            style = MaterialTheme.typography.labelSmall,
                            color = CyberOnSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color(0xFF072425))
                                .padding(horizontal = 12.dp, vertical = 10.dp)
                        ) {
                            Text(
                                text = user.accountCreated,
                                style = MaterialTheme.typography.bodyMedium,
                                color = CyberOnSurface
                            )
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        Text(
                            text = "Last Sign In",
                            style = MaterialTheme.typography.labelSmall,
                            color = CyberOnSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color(0xFF072425))
                                .border(1.dp, Color(0xFF0D5E5E), ChamferedShape(topRightCut = 6.dp))
                                .padding(horizontal = 12.dp, vertical = 10.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = user.lastSignIn,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = CyberOnSurface
                                )
                                Box(
                                    modifier = Modifier
                                        .size(8.dp)
                                        .clip(CircleShape)
                                        .background(CyberPrimary)
                                )
                            }
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}
