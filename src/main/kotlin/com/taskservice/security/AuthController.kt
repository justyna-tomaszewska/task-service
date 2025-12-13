package com.taskservice.security

import org.slf4j.LoggerFactory
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    val authService: AuthService
) {

    private val logger = LoggerFactory.getLogger(AuthController::class.java)

    @PostMapping("/login", consumes = [APPLICATION_JSON_VALUE], produces = [APPLICATION_JSON_VALUE])
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<LoginResponse> {
        logger.info("Login request received for user: ${loginRequest.username}")
        val response = authService.authenticate(loginRequest)
        logger.info("Login successful for user: ${loginRequest.username}")
        return ResponseEntity.ok(response)
    }
}