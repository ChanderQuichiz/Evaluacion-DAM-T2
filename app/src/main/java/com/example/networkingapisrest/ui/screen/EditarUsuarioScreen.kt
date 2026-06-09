package com.example.networkingapisrest.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.networkingapisrest.data.local.AppDatabase
import com.example.networkingapisrest.data.local.UserEntity
import com.example.networkingapisrest.data.repository.UserEntityRepository
import com.example.networkingapisrest.viewmodel.UserEntityViewModel
import com.example.networkingapisrest.viewmodel.ViewModelFactory


@Composable
fun EditarUsuarioScreen(
    id: Int,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val db = AppDatabase.getInstance(context)
    val repository = UserEntityRepository(db.userDao())
    val viewModel: UserEntityViewModel = viewModel(
        factory = ViewModelFactory { UserEntityViewModel(repository) }
    )

    val userToEdit by viewModel.selectedUser.collectAsState()
    var formulario by remember { mutableStateOf(UserEntity()) }
    var showDeleteDialog by remember { mutableStateOf(false) }

    LaunchedEffect(id) {
        viewModel.loadUser(id)
    }

    LaunchedEffect(userToEdit) {
        userToEdit?.let { formulario = it }
    }

    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("¿Eliminar usuario?") },
            text = { Text("Esta acción no se puede deshacer.") },
            confirmButton = {
                TextButton(onClick = {
                    if (formulario.id != 0) viewModel.delete(formulario)
                    showDeleteDialog = false
                    onBack()
                }) {
                    Text("Eliminar")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }

    Scaffold(
        topBar = {
            Column {
                Spacer(modifier = Modifier.height(30.dp))
                Row(
                    modifier = Modifier
                        .padding(10.dp, 6.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { onBack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, null)
                    }

                    Spacer(modifier = Modifier.width(20.dp))

                    Text("Editar Usuario", fontSize = 20.sp)

                    Spacer(modifier = Modifier.width(150.dp))

                    IconButton(onClick = { showDeleteDialog = true }) {
                        Icon(Icons.Default.Delete, null)
                    }
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = formulario.name,
                onValueChange = { value ->
                    formulario = formulario.copy(name = value)
                },
                label = { Text("Nombre completo") }
            )

            OutlinedTextField(
                value = formulario.username,
                onValueChange = { value ->
                    formulario = formulario.copy(username = value)
                },
                label = { Text("Usuario") }
            )

            OutlinedTextField(
                value = formulario.email,
                onValueChange = { value ->
                    formulario = formulario.copy(email = value)
                },
                label = { Text("Correo electronico") }
            )

            OutlinedTextField(
                value = formulario.password,
                onValueChange = { value ->
                    formulario = formulario.copy(password = value)
                },
                label = { Text("Contrasenia") }
            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    if (formulario.id != 0) viewModel.update(formulario)
                    onBack()
                },
                modifier = Modifier.fillMaxWidth(0.7f)
            ) {
                Text("ACTUALIZAR")
            }

            Button(
                onClick = { showDeleteDialog = true },
                modifier = Modifier.fillMaxWidth(0.7f)
            ) {
                Text("ELIMINAR USUARIO")
            }
        }
    }
}