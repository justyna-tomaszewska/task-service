package com.taskservice.security

import org.slf4j.LoggerFactory
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class CustomAuthenticationManager(
    private val userDetailsService: UserDetailsService,
    private val passwordEncoder: PasswordEncoder
) : AuthenticationManager {

    private val logger = LoggerFactory.getLogger(CustomAuthenticationManager::class.java)

    override fun authenticate(authenticaion: Authentication): Authentication {
        logger.debug("Attempting to authenticate user: ${authenticaion.name}")

        val userDetails: UserDetails = userDetailsService.loadUserByUsername(authenticaion.name)

        if (passwordEncoder.matches(authenticaion.credentials.toString(), userDetails.password)) {
            logger.info("Authentication successful for user: ${authenticaion.name}")
            return UsernamePasswordAuthenticationToken(
                userDetails,
                authenticaion.credentials,
                userDetails.authorities
            )
        } else {
            logger.warn("Authentication failed for user: ${authenticaion.name} - Invalid credentials")
            throw IllegalArgumentException("Invalid username or password")
        }
    }

}
