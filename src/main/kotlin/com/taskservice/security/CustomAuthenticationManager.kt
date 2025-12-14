package com.taskservice.security

import com.taskservice.user.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class CustomAuthenticationManager(
    private val userRepository: UserRepository,
    private val passwordUtil: PasswordUtil
) {

    private val logger = LoggerFactory.getLogger(CustomAuthenticationManager::class.java)

    fun authenticate(username: String, password: String): Boolean {
        logger.debug("Attempting to authenticate user: $username")

        val user = userRepository.findByUsername(username)
            ?: run {
                logger.warn("Authentication failed for user: $username - User not found")
                throw IllegalArgumentException("Invalid username or password")
            }

        val isPasswordValid = passwordUtil.verifyPassword(password, user.password)

        if (isPasswordValid) {
            logger.info("Authentication successful for user: $username")
            return true
        } else {
            logger.warn("Authentication failed for user: $username - Invalid credentials")
            throw IllegalArgumentException("Invalid username or password")
        }
    }

}
