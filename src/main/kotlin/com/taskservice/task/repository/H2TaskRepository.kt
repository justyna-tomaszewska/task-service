package com.taskservice.task.repository

import com.taskservice.task.controller.TaskUpdateRequest
import com.taskservice.task.service.Task
import org.springframework.context.annotation.Profile
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

interface TaskJpaRepository : JpaRepository<Task, String>

@Repository
@Profile("!mongo")
class H2TaskRepository(
    private val jpaRepository: TaskJpaRepository
) : TaskRepository {
    override fun save(task: Task): Task {
        return jpaRepository.save(task)
    }

    override fun findById(id: String): Task {
        return jpaRepository.findById(id).orElseThrow { NoSuchElementException("Task not found") }
    }

    override fun update(
        id: String,
        task: TaskUpdateRequest
    ): Task {
        val existingTask = jpaRepository.findById(id).orElseThrow { NoSuchElementException("Task not found") }
        val updatedTask = existingTask.copy(
            name = task.name?: existingTask.name,
            description = task.description?: existingTask.description,
            status = task.status?: existingTask.status
        )
        return jpaRepository.save(updatedTask)
    }

}