package com.cibertec.usuarioslocalest2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UsuariosLocalesScreen()
        }
    }
}

data class UsuarioLocal(
    val nombre: String,
    val usuario: String,
    val correo: String,
    val password: String
)

@Composable
fun UsuariosLocalesScreen() {
    val usuarios = listOf(
        UsuarioLocal("Juan Pérez", "juanp", "juan@gmail.com", "123456"),
        UsuarioLocal("María García", "mariag", "maria@gmail.com", "123456"),
        UsuarioLocal("Carlos López", "carlosl", "carlos@gmail.com", "123456")
    )

    var pantallaEditar by remember { mutableStateOf(false) }
    var usuarioSeleccionado by remember { mutableStateOf(usuarios[0]) }

    if (pantallaEditar) {
        EditarUsuarioScreen(
            usuario = usuarioSeleccionado,
            volver = { pantallaEditar = false }
        )
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Usuarios locales",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Lista de usuarios registrados en el dispositivo",
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(usuarios) { usuario ->
                    UsuarioItem(
                        usuario = usuario,
                        editar = {
                            usuarioSeleccionado = usuario
                            pantallaEditar = true
                        }
                    )
                }
            }
        }
    }
}
var mostrarDialogo by remember { mutableStateOf(false) }
@Composable
fun UsuarioItem(
    usuario: UsuarioLocal,
    editar: () -> Unit
) {
    var expandido by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = CircleShape,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(42.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = usuario.nombre.first().toString(),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(usuario.nombre, fontWeight = FontWeight.Bold)
                Text("@${usuario.usuario}")
                Text(usuario.correo)
            }

            Box {
                IconButton(onClick = { expandido = true }) {
                    Icon(Icons.Default.MoreVert, contentDescription = null)
                }

                DropdownMenu(
                    expanded = expandido,
                    onDismissRequest = { expandido = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Editar") },
                        leadingIcon = {
                            Icon(Icons.Default.Edit, contentDescription = null)
                        },
                        onClick = {
                            expandido = false
                            mostrarDialogo = true
                        }
                    )
                    if (mostrarDialogo) {
                        AlertDialog(
                            onDismissRequest = { mostrarDialogo = false },
                            title = { Text("Eliminar usuario") },
                            text = {
                                Text("¿Estás seguro de que deseas eliminar este usuario? Esta acción no se puede deshacer.")
                            },
                            confirmButton = {
                                TextButton(onClick = { mostrarDialogo = false }) {
                                    Text("Eliminar")
                                }
                            },
                            dismissButton = {
                                TextButton(onClick = { mostrarDialogo = false }) {
                                    Text("Cancelar")
                                }
                            }
                        )
                    }
                    DropdownMenuItem(
                        text = { Text("Eliminar") },
                        leadingIcon = {
                            Icon(Icons.Default.Delete, contentDescription = null)
                        },
                        onClick = {
                            expandido = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun EditarUsuarioScreen(
    usuario: UsuarioLocal,
    volver: () -> Unit
) {
    var nombre by remember { mutableStateOf(usuario.nombre) }
    var user by remember { mutableStateOf(usuario.usuario) }
    var correo by remember { mutableStateOf(usuario.correo) }
    var password by remember { mutableStateOf(usuario.password) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Editar usuario",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Formulario con datos previamente cargados",
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre completo") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = user,
            onValueChange = { user = it },
            label = { Text("Usuario") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo electrónico") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { volver() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("ACTUALIZAR")
        }

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedButton(
            onClick = { volver() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("ELIMINAR USUARIO")
        }
    }
}