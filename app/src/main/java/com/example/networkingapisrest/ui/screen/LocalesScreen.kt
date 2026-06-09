package com.example.networkingapisrest.ui.screen

import NavigationBarExample
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import okhttp3.Route

@Composable
fun LocalesScreen(
    onUserScreen:()-> Unit,
    onLocalesScreen:()-> Unit
    ,currentRoute: String
) {
    Scaffold(
        bottomBar = {
        NavigationBarExample(
            onUserScreen,
            onLocalesScreen,
            currentRoute
        )
        }

    ) {
        paddingValues ->
        Text("ds", modifier = Modifier.padding(paddingValues))
    }
}