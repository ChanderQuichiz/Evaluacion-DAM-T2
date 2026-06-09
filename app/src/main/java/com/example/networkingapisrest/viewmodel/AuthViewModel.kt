package com.example.networkingapisrest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.networkingapisrest.data.local.UserEntity
import com.example.networkingapisrest.data.repository.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository
) : ViewModel() {

    fun registrar(user: UserEntity) {
        viewModelScope.launch {
            repository.registrar(user)
        }
    }

    suspend fun login(
        usuario: String,
        password: String
    ): Boolean {

        return repository.login(
            usuario,
            password
        ) != null
    }
}