package com.example.networkingapisrest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.networkingapisrest.ui.navigation.AppNavigation
import com.example.networkingapisrest.ui.theme.NetworkingApisRestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NetworkingApisRestTheme {
                AppNavigation()
            }
        }
    }
}
