package com.taskservice.task.service

import com.taskservice.security.AuthService
import com.taskservice.task.controller.TaskDTO
import com.taskservice.task.controller.TaskUpdateRequest
import com.taskservice.task.repository.TaskRepository
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class TaskService(
    val taskRepository: TaskRepository
) {
    private val logger = LoggerFactory.getLogger(AuthService::class.java)

    @Cacheable(value = ["tasks"], key = "#id")
    fun findTaskById(id: String): Task {
        logger.info("Fetching task with id: $id from database")
        return taskRepository.findById(id)
    }

    @CacheEvict(value = ["tasks"], key = "#result.id")
    fun saveTask(task: Task): Task {
        logger.info("Saving task: $task and evicting from cache")
        return taskRepository.save(task)
    }

    @CachePut(value = ["tasks"], key = "#id")
    fun updateTask(id: String, task: TaskUpdateRequest): Task {
        logger.info("Updating task with id: $id and updating cache")
        return taskRepository.update(id, task)
    }
}
