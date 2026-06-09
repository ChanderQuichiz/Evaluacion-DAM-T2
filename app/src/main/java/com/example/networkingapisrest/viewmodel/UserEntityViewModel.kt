package com.example.networkingapisrest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.networkingapisrest.data.local.UserEntity
import com.example.networkingapisrest.data.repository.UserEntityRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserEntityViewModel(private val repository: UserEntityRepository): ViewModel() {
    val users: StateFlow<List<UserEntity>> = repository.allusers.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    private val _selectedUser = MutableStateFlow<UserEntity?>(null)
    val selectedUser: StateFlow<UserEntity?> = _selectedUser

    fun loadUser(id: Int) = viewModelScope.launch {
        repository.getUserById(id).collect {
            _selectedUser.value = it
        }
    }

    fun update(user: UserEntity) = viewModelScope.launch {
        repository.update(user)
    }

    fun delete(user: UserEntity) = viewModelScope.launch {
        repository.delete(user)
    }
}