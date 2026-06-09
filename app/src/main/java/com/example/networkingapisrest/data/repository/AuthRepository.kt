package com.example.networkingapisrest.data.repository

import com.example.networkingapisrest.data.local.UserDao
import com.example.networkingapisrest.data.local.UserEntity

class AuthRepository(
    private val userDao: UserDao
) {

    suspend fun registrar(
        user: UserEntity
    ) {
        userDao.insert(user)
    }

    suspend fun login(
        usuario: String,
        password: String
    ): UserEntity? {

        return userDao.login(
            usuario,
            password
        )
    }
}