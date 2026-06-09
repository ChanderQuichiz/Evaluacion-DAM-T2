package com.example.networkingapisrest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
class ViewModelFactory<T : ViewModel>(
    private val creator: () -> T
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return try {
            creator() as T
        } catch (e: Exception) {
            throw IllegalArgumentException("No se puede crear el ViewModel: ${modelClass.simpleName}", e)
        }
    }
}