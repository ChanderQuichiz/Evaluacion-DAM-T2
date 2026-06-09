package com.example.networkingapisrest.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios_locales")
data class UserLocalEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombreCompleto: String,
    val usuario: String,
    val correo: String,
    val contrasena: String
)