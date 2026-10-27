package com.project.pelisense.ui.components.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun SecurityCard(user: UserData) {
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