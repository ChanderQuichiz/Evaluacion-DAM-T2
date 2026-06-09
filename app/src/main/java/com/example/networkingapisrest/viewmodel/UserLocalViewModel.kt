package com.example.networkingapisrest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.networkingapisrest.data.local.UserLocalEntity
import com.example.networkingapisrest.data.repository.UserLocalRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserLocalViewModel(
    private val repository: UserLocalRepository
) : ViewModel() {

    val usuariosLocales: StateFlow<List<UserLocalEntity>> =
        repository.obtenerUsuariosLocales()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    fun registrarUsuario(
        nombreCompleto: String,
        usuario: String,
        correo: String,
        contrasena: String
    ) {
        viewModelScope.launch {
            val nuevoUsuario = UserLocalEntity(
                nombreCompleto = nombreCompleto,
                usuario = usuario,
                correo = correo,
                contrasena = contrasena
            )

            repository.registrarUsuario(nuevoUsuario)
        }
    }

    fun actualizarUsuario(usuario: UserLocalEntity) {
        viewModelScope.launch {
            repository.actualizarUsuario(usuario)
        }
    }

    fun eliminarUsuario(usuario: UserLocalEntity) {
        viewModelScope.launch {
            repository.eliminarUsuario(usuario)
        }
    }
}