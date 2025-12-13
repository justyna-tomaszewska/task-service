package com.taskservice.task.service

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.data.mongodb.core.mapping.Document

@Entity
@Table(name = "tasks")
@Document("tasks")
data class Task(
    @Id
    val id: String,
    val name: String,
    val description: String,
    val status: String
)