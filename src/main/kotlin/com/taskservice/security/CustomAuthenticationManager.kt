package com.taskservice.security

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

    override fun authenticate(authenticaion: Authentication): Authentication {

        val userDetails: UserDetails = userDetailsService.loadUserByUsername(authenticaion.name)

        if (passwordEncoder.matches(authenticaion.credentials.toString(), userDetails.password)) {
            return UsernamePasswordAuthenticationToken(
                userDetails,
                authenticaion.credentials,
                userDetails.authorities
            )
        } else {
            throw IllegalArgumentException("Invalid username or password")
        }
    }

}
