package com.taskservice.task.repository

import com.taskservice.task.controller.TaskUpdateRequest
import com.taskservice.task.service.Task

interface TaskRepository{
    fun save(task: Task): Task
    fun findById(id: String): Task
    fun update(id: String, task: TaskUpdateRequest): Task
}


