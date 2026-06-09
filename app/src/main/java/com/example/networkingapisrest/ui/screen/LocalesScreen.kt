package com.example.networkingapisrest.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Abc
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.People
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.networkingapisrest.data.local.AppDatabase
import com.example.networkingapisrest.data.local.UserEntity
import com.example.networkingapisrest.data.repository.UserEntityRepository
import com.example.networkingapisrest.ui.components.NavigationBarExample
import com.example.networkingapisrest.viewmodel.UserEntityViewModel
import com.example.networkingapisrest.viewmodel.ViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocalesScreen(
    onUserScreen: () -> Unit,
    onLocalesScreen: () -> Unit,
    currentRoute: String,
    onEditarUsuario: (UserEntity) -> Unit,
    onRegisterScreen: () -> Unit
) {
    val context = LocalContext.current
    val db = remember { AppDatabase.getInstance(context) }
    val repository = remember { UserEntityRepository(db.userDao()) }
    val viewModel: UserEntityViewModel = viewModel(
        factory = ViewModelFactory { UserEntityViewModel(repository) }
    )

    val users by viewModel.users.collectAsState()

    Scaffold(
        bottomBar = {
            NavigationBarExample(
                onUserScreen,
                onLocalesScreen,
                currentRoute
            )
        },
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.People,
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.padding(4.dp))
                        Text(
                            text = "Usuarios locales",
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            if (users.isEmpty()) {
                Text(
                    text = "No hay usuarios locales todavía",
                    style = MaterialTheme.typography.bodyLarge
                )
            } else {
                LazyColumn {
                    items(users) { user ->
                        ElevatedCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.fillMaxWidth(0.82f)) {
                                    Text(user.name, fontWeight = FontWeight.Bold)
                                    Text("@${user.username}")
                                    Text(user.email)
                                }
                                IconButton(onClick = { onEditarUsuario(user) }) {
                                    Icon(Icons.Default.Edit, contentDescription = "Editar")
                                }
                            }
                        }
                    }
                }
            }
        }


        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            // Contenido principal

            FloatingActionButton(
                onClick = {
                    onRegisterScreen()
                },
                shape = CircleShape
                ,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp,160.dp)
                    .size(50.dp)

            ) {
                IconButton(
                    onClick = {
                        onRegisterScreen()
                    }
                ) {
                    Icon(Icons.Default.Add,null)
                }
            }
        }


    }
}
