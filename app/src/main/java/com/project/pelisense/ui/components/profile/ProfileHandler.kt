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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.pelisense.domain.data.UserData
import com.project.pelisense.ui.components.CyberButton
import com.project.pelisense.ui.components.CyberOutlinedButton
import com.project.pelisense.ui.components.PelisenseLogoIcon
import com.project.pelisense.ui.theme.CyberOnSurfaceVariant
import com.project.pelisense.ui.theme.CyberPrimary
import com.project.pelisense.ui.theme.CyberPrimaryBright
import com.project.pelisense.ui.theme.TechFontFamily


@Composable
fun ProfileHandler(
    user: UserData,
    onSignOutClick: () -> Unit
) {
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
