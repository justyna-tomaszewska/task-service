package com.taskservice.task.controller

import com.taskservice.task.service.Task

data class TaskDTO(
    val id: String,
    val name: String,
    val description: String
){
    companion object {
        fun from(task: Task): TaskDTO =
            TaskDTO(
                id = task.id,
                name = task.name,
                description = task.description
            )
    }
}