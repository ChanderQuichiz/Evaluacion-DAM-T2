package com.example.networkingapisrest.ui.screen


import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.networkingapisrest.data.local.UserEntity
import com.example.networkingapisrest.routes.Login
import com.example.networkingapisrest.viewmodel.AuthViewModel

@Composable
fun CrearUsuarioScreen(
    onBack:()->Unit,
    authViewModel: AuthViewModel
) {

    var usuario by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }


    Scaffold(
        topBar = {
            Column() {
                Spacer(modifier = Modifier.height(30.dp))
                Row(
                    modifier = Modifier.padding(10.dp,6.dp).fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { onBack()}
                    ) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack,null)
                    }

                    Spacer(modifier = Modifier.width(20.dp))

                    Text("Registrar Usuario", fontSize = 20.sp)



                }
            }



        }
    ) { paddingValues ->

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Column(
                modifier = Modifier
                    .fillMaxSize(0.7f)
            ) {

                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre Completo") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = usuario,
                    onValueChange = { usuario = it },
                    label = { Text("Usuario") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = correo,
                    onValueChange = { correo = it },
                    label = { Text("Correo") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Contraseña") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))



                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {

                        when {

                            usuario.isEmpty() ||
                                    nombre.isEmpty()||
                                    correo.isEmpty() ||
                                    password.isEmpty()-> {

                                error = "Complete todos los campos"
                            }

                         

                            else -> {

                                authViewModel.registrar(
                                    UserEntity(
                                        name = nombre,
                                        username = usuario,
                                        email = correo,
                                        password = password
                                    )
                                )
                                onBack()
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("GUARDAR")
                }

                Spacer(modifier = Modifier.height(8.dp))

                if (error.isNotEmpty()) {

                    Text(
                        text = error,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}