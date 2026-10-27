package com.project.pelisense.ui.components.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.project.pelisense.domain.data.UserData
import com.project.pelisense.ui.components.CyberGlassCard
import com.project.pelisense.ui.components.LaserDivider
import com.project.pelisense.ui.theme.ChamferedShape
import com.project.pelisense.ui.theme.CyberOnSurface
import com.project.pelisense.ui.theme.CyberOnSurfaceVariant
import com.project.pelisense.ui.theme.CyberPrimary
import com.project.pelisense.ui.theme.CyberPrimaryBright
import com.project.pelisense.ui.theme.TechFontFamily


@Composable
fun HistoryLogCard(user: UserData) {
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
                    text = user.accountCreated.toString(),
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
                        text = user.lastSignIn.toString(),
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