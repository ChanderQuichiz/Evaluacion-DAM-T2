package com.example.networkingapisrest.data.repository

import com.example.networkingapisrest.data.local.UserLocalEntity
import kotlinx.coroutines.flow.Flow

interface UserLocalRepository {

    fun obtenerUsuariosLocales(): Flow<List<UserLocalEntity>>

    suspend fun registrarUsuario(usuario: UserLocalEntity)

    suspend fun actualizarUsuario(usuario: UserLocalEntity)

    suspend fun eliminarUsuario(usuario: UserLocalEntity)

    suspend fun obtenerUsuarioPorId(id: Int): UserLocalEntity?
}