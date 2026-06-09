package com.example.networkingapisrest.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert
    suspend fun insert(user: UserEntity)

    @Query(
        "SELECT * FROM usuarios WHERE usuario = :usuario AND password = :password"
    )
    suspend fun login(
        usuario: String,
        password: String
    ): UserEntity?
}