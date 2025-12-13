package com.taskservice.user.controller

import com.taskservice.user.service.User

data class UserDTO(
    val id: String,
    val username: String,
    val email: String,
    val role: String
){
    companion object {
        fun from(user: User): UserDTO =
            UserDTO(
                id = user.id,
                username = user.username,
                email = user.email,
                role = user.role
            )
    }
}

