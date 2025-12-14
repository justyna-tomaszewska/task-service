package com.taskservice.user.service

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.data.mongodb.core.mapping.Document

@Entity
@Table(name = "users")
@Document("users")
data class User(
    @Id
    val id: String,
    val username: String,
    val email: String,
    val password: String, // Hashed password
    val role: String
)


