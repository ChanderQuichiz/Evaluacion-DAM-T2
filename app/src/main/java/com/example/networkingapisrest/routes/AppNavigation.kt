package com.example.networkingapisrest.routes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.networkingapisrest.data.local.AppDatabase
import com.example.networkingapisrest.data.local.UserEntity
import com.example.networkingapisrest.data.repository.AuthRepository
import com.example.networkingapisrest.ui.screen.CrearUsuarioScreen
import com.example.networkingapisrest.ui.screen.EditarUsuarioScreen
import com.example.networkingapisrest.ui.screen.LocalesScreen
import com.example.networkingapisrest.ui.screen.LoginScreen
import com.example.networkingapisrest.ui.screen.RegisterScreen
import com.example.networkingapisrest.ui.screen.UserScreen
import com.example.networkingapisrest.viewmodel.AuthViewModel
import com.example.networkingapisrest.viewmodel.AuthViewModelFactory

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current

    val db = remember(context) {
        AppDatabase.getInstance(context)
    }
    val authRepository = AuthRepository(db.userDao())
    val authViewModel: AuthViewModel = viewModel(
        factory = AuthViewModelFactory(authRepository)
    )

    NavHost(
        navController = navController,
        startDestination = Login
    ) {
        composable<Login> {
            LoginScreen(
                navController = navController,
                authViewModel = authViewModel
            )
        }

        composable<Register> {
            RegisterScreen(
                navController = navController,
                authViewModel = authViewModel
            )
        }

        composable<Home> {
            UserScreen(
                onUserScreen = { navController.navigate(Home) },
                onLocalesScreen = { navController.navigate(Locales) },
                currentRoute = Home.toString(),
                onEditarUsuario = { user ->
                    navController.navigate(EditarUsuario(user.id))
                }
            )
        }

        composable<Locales> {
            LocalesScreen(
                onUserScreen = { navController.navigate(Home) },
                onLocalesScreen = { navController.navigate(Locales) },
                currentRoute = Locales.toString(),
                onEditarUsuario = { user: UserEntity ->
                    navController.navigate(EditarUsuario(user.id))
                },
                onRegisterScreen = { navController.navigate(CrearUsuario )}
            )
        }

        composable<EditarUsuario> { backStackEntry ->
            val args = backStackEntry.toRoute<EditarUsuario>()
            EditarUsuarioScreen(
                id = args.id,
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable<CrearUsuario> {
            CrearUsuarioScreen(
                onBack = {
                    navController.popBackStack()
                },
                authViewModel = authViewModel
            )
        }

    }
}
