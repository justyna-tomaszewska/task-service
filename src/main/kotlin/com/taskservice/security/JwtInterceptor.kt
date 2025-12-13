package com.taskservice.security

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.web.servlet.HandlerInterceptor

class JwtInterceptor(
    private val jwtUtil: JwtUtil
) : HandlerInterceptor {

    private val logger = LoggerFactory.getLogger(JwtInterceptor::class.java)

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        logger.debug("JWT interceptor processing request to: ${request.requestURI}")
        val authHeader = request.getHeader("Authorization")

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            logger.warn("Request to ${request.requestURI} rejected: Missing or invalid Authorization header")
            response.status = HttpServletResponse.SC_UNAUTHORIZED
            response.writer.write("{\"error\":\"Missing or invalid Authorization header\"}")
            return false
        }

        val token = authHeader.substring(7)

        if (!jwtUtil.validateToken(token)) {
            logger.warn("Request to ${request.requestURI} rejected: Invalid or expired token")
            response.status = HttpServletResponse.SC_UNAUTHORIZED
            response.writer.write("{\"error\":\"Invalid or expired token\"}")
            return false
        }

        val username = jwtUtil.getUsernameFromToken(token)
        logger.debug("JWT validation successful for user: $username, accessing: ${request.requestURI}")
        request.setAttribute("username", username)

        return true
    }
}