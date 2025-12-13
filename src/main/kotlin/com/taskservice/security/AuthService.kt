package com.taskservice.security

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import javax.naming.AuthenticationException

@Service
class AuthService(
    private val authenticationManager: CustomAuthenticationManager,
    private val jwtUtil: JwtUtil
) {

    fun authenticate(loginRequest: LoginRequest): LoginResponse {
        return runCatching {
            val auth: Authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    loginRequest.username,
                    loginRequest.password
                )
            )
            val token: String = jwtUtil.generateToken(loginRequest.username)
            LoginResponse(token)
        }.getOrElse { e ->
            when (e) {
                is AuthenticationException -> throw IllegalArgumentException("Invalid username or password")
                else -> throw e
            }
        }
    }
}