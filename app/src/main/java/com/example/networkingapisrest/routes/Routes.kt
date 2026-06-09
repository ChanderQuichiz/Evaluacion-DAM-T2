package com.example.networkingapisrest.routes

import com.example.networkingapisrest.data.model.User
import kotlinx.serialization.Serializable

@Serializable
object Login

@Serializable
object Register

@Serializable
object Home

@Serializable
object Locales
@Serializable
object NavigationBarExample
@Serializable
data class EditarUsuario(val id: Int)