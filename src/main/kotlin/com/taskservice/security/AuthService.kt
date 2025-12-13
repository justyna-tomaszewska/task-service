package com.taskservice.security

import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service
import javax.naming.AuthenticationException

@Service
class AuthService(
    private val authenticationManager: CustomAuthenticationManager,
    private val jwtUtil: JwtUtil
) {

    private val logger = LoggerFactory.getLogger(AuthService::class.java)

    fun authenticate(loginRequest: LoginRequest): LoginResponse {
        logger.debug("Authentication request received for user: ${loginRequest.username}")
        return runCatching {
             authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    loginRequest.username,
                    loginRequest.password
                )
            )
            val token: String = jwtUtil.generateToken(loginRequest.username)
            logger.info("JWT token generated for user: ${loginRequest.username}")
            LoginResponse(token)
        }.getOrElse { e ->
            logger.error("Authentication failed for user: ${loginRequest.username}", e)
            when (e) {
                is AuthenticationException -> throw IllegalArgumentException("Invalid username or password")
                else -> throw e
            }
        }
    }
}