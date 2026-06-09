package com.example.networkingapisrest.data.repository

import com.example.networkingapisrest.dao.UserDao
import com.example.networkingapisrest.data.model.UserLocal
import kotlinx.coroutines.flow.Flow

class UserLocalRepository(private val dao: UserDao) {
    val allusers: Flow<List<UserLocal>> = dao.getAllUsers()
    suspend fun  update(user: UserLocal) = dao.update(user)
}