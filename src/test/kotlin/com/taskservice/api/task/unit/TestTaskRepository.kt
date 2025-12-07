package com.taskservice.api.task.unit

import com.taskservice.task.controller.TaskDTO
import com.taskservice.task.controller.TaskUpdateRequest
import com.taskservice.task.repository.TaskRepository
import com.taskservice.task.service.Task

class TestTaskRepository : TaskRepository {

    private val tasks = mutableListOf<Task>()

    override fun save(task: Task): Task {
        tasks.add(task)
        return task
    }

    override fun findById(id: String): Task {
        return tasks.find { task -> task.id == id }
            ?: throw NoSuchElementException("Task with id $id not found")
    }

    override fun update(id: String, task: TaskUpdateRequest): Task {
        val index: Int = tasks.indexOfFirst { task -> task.id == id }
        if (index == -1) {
            throw NoSuchElementException("Task with id $id not found")
        }

        tasks[index] = tasks[index].copy(
            name = task.name?: tasks[index].name,
            description = task.description?: tasks[index].description,
            status = task.status?: tasks[index].status
        )
        return tasks.find { task -> task.id == id }
            ?: throw NoSuchElementException("Task with id $id not found")
    }
}