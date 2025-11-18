package com.taskservice.task.repository

import com.taskservice.task.service.Task

interface TaskRepository{
    fun save(task: Task): String
    fun findById(id: String): Task
}


