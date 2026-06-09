package com.example.networkingapisrest.dao



import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.example.networkingapisrest.data.model.UserLocal
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao {
        @Query("SELECT * FROM users ORDER BY id ASC")
        fun getAllUsers(): Flow<List<UserLocal>>
        @Update
        suspend fun update(user: UserLocal)
}