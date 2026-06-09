package com.example.networkingapisrest.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserLocalDao {

    @Query("SELECT * FROM usuarios_locales ORDER BY id DESC")
    fun obtenerUsuariosLocales(): Flow<List<UserLocalEntity>>

    @Insert
    suspend fun registrarUsuario(usuario: UserLocalEntity)

    @Update
    suspend fun actualizarUsuario(usuario: UserLocalEntity)

    @Delete
    suspend fun eliminarUsuario(usuario: UserLocalEntity)

    @Query("SELECT * FROM usuarios_locales WHERE id = :id LIMIT 1")
    suspend fun obtenerUsuarioPorId(id: Int): UserLocalEntity?
}