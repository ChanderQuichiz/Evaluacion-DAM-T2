package com.example.networkingapisrest.data.repository

import com.example.networkingapisrest.data.local.UserDao
import com.example.networkingapisrest.data.local.UserEntity
import kotlinx.coroutines.flow.Flow

class UserEntityRepository(private val dao: UserDao) {
    val allusers: Flow<List<UserEntity>> = dao.getAllUsers()
    fun getUserById(id: Int): Flow<UserEntity?> = dao.getUserById(id)
    suspend fun update(user: UserEntity) = dao.update(user)
    suspend fun delete(user: UserEntity) = dao.delete(user)
}