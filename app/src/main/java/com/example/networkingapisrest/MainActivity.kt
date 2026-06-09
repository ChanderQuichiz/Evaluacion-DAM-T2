package com.example.networkingapisrest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.networkingapisrest.data.local.AppDatabase
import com.example.networkingapisrest.data.repository.UserLocalRepositoryImpl
import com.example.networkingapisrest.ui.screen.NuevoUsuarioScreen
import com.example.networkingapisrest.ui.screen.UserScreen
import com.example.networkingapisrest.ui.screen.UsuariosLocalesScreen
import com.example.networkingapisrest.ui.theme.NetworkingApisRestTheme
import com.example.networkingapisrest.viewmodel.UserLocalViewModel
import com.example.networkingapisrest.viewmodel.UserLocalViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            NetworkingApisRestTheme {

                val context = LocalContext.current

                val database = remember {
                    AppDatabase.obtenerBaseDatos(context)
                }

                val repository = remember {
                    UserLocalRepositoryImpl(database.userLocalDao())
                }

                val factory = remember {
                    UserLocalViewModelFactory(repository)
                }

                val userLocalViewModel: UserLocalViewModel = viewModel(
                    factory = factory
                )

                val navController = rememberNavController()
                val navBackStackEntry = navController.currentBackStackEntryAsState()
                val rutaActual = navBackStackEntry.value?.destination?.route

                Scaffold(
                    bottomBar = {
                        NavigationBar {
                            NavigationBarItem(
                                selected = rutaActual == "home",
                                onClick = {
                                    navController.navigate("home") {
                                        popUpTo("home") {
                                            inclusive = true
                                        }
                                    }
                                },
                                icon = {
                                    Icon(
                                        imageVector = Icons.Default.Home,
                                        contentDescription = "Home"
                                    )
                                },
                                label = {
                                    Text("Home")
                                }
                            )

                            NavigationBarItem(
                                selected = rutaActual == "locales",
                                onClick = {
                                    navController.navigate("locales")
                                },
                                icon = {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.List,
                                        contentDescription = "Locales"
                                    )
                                },
                                label = {
                                    Text("Locales")
                                }
                            )
                        }
                    }
                ) { paddingValues ->

                    NavHost(
                        navController = navController,
                        startDestination = "home",
                        modifier = Modifier.padding(paddingValues)
                    ) {
                        composable("home") {
                            UserScreen()
                        }

                        composable("locales") {
                            UsuariosLocalesScreen(
                                viewModel = userLocalViewModel,
                                onNuevoUsuarioClick = {
                                    navController.navigate("nuevo_usuario")
                                },
                                onEditarUsuarioClick = {
                                    // Pendiente para pantalla de editar
                                }
                            )
                        }

                        composable("nuevo_usuario") {
                            NuevoUsuarioScreen(
                                viewModel = userLocalViewModel,
                                onVolverClick = {
                                    navController.popBackStack()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}