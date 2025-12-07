package com.taskservice.task.controller

data class TaskUpdateRequest(
    val name: String?,
    val description: String?,
    val status: String?
)