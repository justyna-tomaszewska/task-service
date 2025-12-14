package com.taskservice.security

import org.springframework.stereotype.Component
import java.security.MessageDigest
import java.security.SecureRandom
import java.util.Base64

@Component
class PasswordUtil {

    fun verifyPassword(plainPassword: String, hashedPassword: String): Boolean {
        return plainPassword == hashedPassword
    }
}

