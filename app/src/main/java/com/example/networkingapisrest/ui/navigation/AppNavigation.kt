package com.example.networkingapisrest.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.networkingapisrest.data.local.AppDatabase
import com.example.networkingapisrest.data.repository.AuthRepository
import com.example.networkingapisrest.ui.screen.LoginScreen
import com.example.networkingapisrest.ui.screen.RegisterScreen
import com.example.networkingapisrest.ui.screen.UserScreen
import com.example.networkingapisrest.viewmodel.AuthViewModel
import com.example.networkingapisrest.viewmodel.AuthViewModelFactory

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    val context = LocalContext.current

    val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "usuarios_db"
    ).build()

    val repository = AuthRepository(
        db.userDao()
    )

    val authViewModel: AuthViewModel = viewModel(
        factory = AuthViewModelFactory(repository)
    )

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        composable("login") {

            LoginScreen(
                navController = navController,
                authViewModel = authViewModel
            )
        }

        composable("register") {

            RegisterScreen(
                navController = navController,
                authViewModel = authViewModel
            )
        }   

        composable("home") {
            UserScreen()
        }
    }
}