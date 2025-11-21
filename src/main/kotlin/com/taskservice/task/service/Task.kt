package com.taskservice.task.service

data class Task(
    val id: String,
    val name: String,
    val description: String,
    val status: String
)