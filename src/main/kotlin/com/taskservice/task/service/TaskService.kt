package com.taskservice.task.service

import com.taskservice.task.controller.TaskDTO
import com.taskservice.task.controller.TaskUpdateRequest
import com.taskservice.task.repository.TaskRepository
import org.springframework.stereotype.Service

@Service
class TaskService(
    val taskRepository: TaskRepository
) {
    fun findTaskById(id: String): Task {
        return taskRepository.findById(id)
    }

    fun saveTask(task: Task): Task {
        return taskRepository.save(task)
    }

    fun updateTask(id: String, task: TaskUpdateRequest): Task {
        return taskRepository.update(id, task)
    }
}
