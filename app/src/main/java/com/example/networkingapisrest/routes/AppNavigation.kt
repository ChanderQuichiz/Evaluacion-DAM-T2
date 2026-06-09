package com.example.networkingapisrest.routes

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.networkingapisrest.ui.screen.EditarUsuarioScreen
import com.example.networkingapisrest.ui.screen.LocalesScreen
import com.example.networkingapisrest.ui.screen.UserScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Home
    ){
        composable<Home>{
            UserScreen(
                onUserScreen = {
                    navController.navigate(Home)
                },
                onLocalesScreen = {
                    navController.navigate(Locales)
                },
                currentRoute = Home.toString(),
               onEditarUsuario = {
                   navController.navigate(EditarUsuario)
               }
            )
        }
        composable <Locales>{
            LocalesScreen(
                onUserScreen = {
                    navController.navigate(Home)
                },
                onLocalesScreen = {
                    navController.navigate(Locales)
                },
                currentRoute = Locales.toString()
            )
        }

        composable <EditarUsuario>{
            backStackEntry ->
            val args = backStackEntry.toRoute<EditarUsuario>()
            EditarUsuarioScreen(
                id = args.id,
                onBack = {
                    navController.popBackStack()
                }
            )
        }

    }

}
