package com.taskservice.security

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

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
                loginRequest.username,
                loginRequest.password
            )
            val token: String = jwtUtil.generateToken(loginRequest.username)
            logger.info("JWT token generated for user: ${loginRequest.username}")
            LoginResponse(token)
        }.getOrElse { e ->
            logger.error("Authentication failed for user: ${loginRequest.username}", e)
            when (e) {
                is IllegalArgumentException -> throw e
                else -> throw IllegalArgumentException("Authentication failed: ${e.message}")
            }
        }
    }
}