package com.project.pelisense

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.project.pelisense.ui.navigation.PelisenseNavHost
import com.project.pelisense.ui.theme.PelisenseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PelisenseTheme {
                PelisenseNavHost()
            }
        }
    }
}
