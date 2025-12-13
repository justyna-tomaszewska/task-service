package com.taskservice.security

data class LoginRequest(
    val username: String,
    val password: String
)