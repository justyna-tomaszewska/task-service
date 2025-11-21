package com.taskservice.api.task

import com.taskservice.task.repository.TaskRepository
import com.taskservice.task.service.Task

class TestTaskRepository: TaskRepository {

    private val tasks = mutableListOf<Task>()

    override fun save(task: Task): Task {
        tasks.add(task)
        return task
    }

    override fun findById(id: String): Task {
        return tasks.find { task -> task.id == id }
            ?: throw NoSuchElementException("Task with id $id not found")
    }
}