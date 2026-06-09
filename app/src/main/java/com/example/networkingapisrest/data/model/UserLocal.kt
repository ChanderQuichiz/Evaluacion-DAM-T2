package com.example.networkingapisrest.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "users")
data class UserLocal(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,


val name: String = "",

val username: String =   "",

val email: String ="",

val password: String ="",

)