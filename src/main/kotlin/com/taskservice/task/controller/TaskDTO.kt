package com.taskservice.task.controller

import com.taskservice.task.service.Task

data class TaskDTO(
    val id: String,
    val name: String,
    val description: String,
    val status: String
){
    companion object {
        fun from(task: Task): TaskDTO =
            TaskDTO(
                id = task.id,
                name = task.name,
                description = task.description,
                status = task.status
            )
    }
}