package com.example.networkingapisrest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.networkingapisrest.data.repository.UserLocalRepository

class UserLocalViewModelFactory(
    private val repository: UserLocalRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserLocalViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserLocalViewModel(repository) as T
        }
        throw IllegalArgumentException("ViewModel desconocido")
    }
}