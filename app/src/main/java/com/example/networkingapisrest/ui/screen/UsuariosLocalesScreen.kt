package com.example.networkingapisrest.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.networkingapisrest.data.local.UserLocalEntity
import com.example.networkingapisrest.viewmodel.UserLocalViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsuariosLocalesScreen(
    viewModel: UserLocalViewModel,
    onNuevoUsuarioClick: () -> Unit,
    onEditarUsuarioClick: (UserLocalEntity) -> Unit
) {
    val usuarios by viewModel.usuariosLocales.collectAsState()
    var usuarioAEliminar by remember { mutableStateOf<UserLocalEntity?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Usuarios locales") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNuevoUsuarioClick
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Nuevo usuario"
                )
            }
        }
    ) { paddingValues ->

        if (usuarios.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "No hay usuarios registrados.",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(8.dp)
            ) {
                items(usuarios) { usuario ->
                    UsuarioLocalItem(
                        usuario = usuario,
                        onEditarClick = {
                            onEditarUsuarioClick(usuario)
                        },
                        onEliminarClick = {
                            usuarioAEliminar = usuario
                        }
                    )
                }
            }
        }
    }

    if (usuarioAEliminar != null) {
        AlertDialog(
            onDismissRequest = {
                usuarioAEliminar = null
            },
            title = {
                Text("Eliminar usuario")
            },
            text = {
                Text(
                    "¿Estás seguro de que deseas eliminar este usuario?\nEsta acción no se puede deshacer."
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        usuarioAEliminar?.let { usuario ->
                            viewModel.eliminarUsuario(usuario)
                        }
                        usuarioAEliminar = null
                    }
                ) {
                    Text("Eliminar")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        usuarioAEliminar = null
                    }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }
}

@Composable
fun UsuarioLocalItem(
    usuario: UserLocalEntity,
    onEditarClick: () -> Unit,
    onEliminarClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = usuario.usuario,
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = usuario.correo,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            IconButton(
                onClick = onEditarClick
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Editar usuario"
                )
            }

            Spacer(modifier = Modifier.width(4.dp))

            IconButton(
                onClick = onEliminarClick
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Eliminar usuario"
                )
            }
        }
    }
}