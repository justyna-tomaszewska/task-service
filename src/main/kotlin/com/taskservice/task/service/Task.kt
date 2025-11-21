package com.taskservice.task.service

import org.springframework.data.mongodb.core.mapping.Document

@Document("tasks")
data class Task(
    val id: String,
    val name: String,
    val description: String,
    val status: String
)