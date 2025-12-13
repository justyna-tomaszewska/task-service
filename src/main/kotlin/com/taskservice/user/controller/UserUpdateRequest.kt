package com.taskservice.user.controller

data class UserUpdateRequest(
    val username: String?,
    val email: String?,
    val role: String?
)

