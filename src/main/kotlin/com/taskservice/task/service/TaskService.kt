package com.taskservice.task.service

import com.taskservice.task.repository.TaskRepository
import org.springframework.stereotype.Service

@Service
class TaskService(
    val taskRepository: TaskRepository
) {
    fun findTaskById(id: String): Task {
        return taskRepository.findById(id)
    }

    fun saveTask(task: Task): String {
        return taskRepository.save(task)
    }
}
