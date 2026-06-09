package com.example.networkingapisrest.data.repository

import com.example.networkingapisrest.data.local.UserLocalDao
import com.example.networkingapisrest.data.local.UserLocalEntity
import kotlinx.coroutines.flow.Flow

class UserLocalRepositoryImpl(
    private val dao: UserLocalDao
) : UserLocalRepository {

    override fun obtenerUsuariosLocales(): Flow<List<UserLocalEntity>> {
        return dao.obtenerUsuariosLocales()
    }

    override suspend fun registrarUsuario(usuario: UserLocalEntity) {
        dao.registrarUsuario(usuario)
    }

    override suspend fun actualizarUsuario(usuario: UserLocalEntity) {
        dao.actualizarUsuario(usuario)
    }

    override suspend fun eliminarUsuario(usuario: UserLocalEntity) {
        dao.eliminarUsuario(usuario)
    }

    override suspend fun obtenerUsuarioPorId(id: Int): UserLocalEntity? {
        return dao.obtenerUsuarioPorId(id)
    }
}