package com.example.networkingapisrest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.networkingapisrest.data.model.UserLocal
import com.example.networkingapisrest.data.repository.UserLocalRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserLocalViewModel(private val repository: UserLocalRepository): ViewModel() {
    val users: StateFlow<List<UserLocal>> = repository.allusers.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )
    fun update(user: UserLocal) = viewModelScope.launch {
        repository.update(user)
    }
}