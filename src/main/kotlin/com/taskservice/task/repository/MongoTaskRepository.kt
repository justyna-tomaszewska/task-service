package com.taskservice.task.repository

import com.taskservice.task.controller.TaskUpdateRequest
import com.taskservice.task.service.Task
import org.springframework.context.annotation.Profile
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.query.Criteria.where
import org.springframework.data.mongodb.core.query.Query.query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.stereotype.Component

@Component
@Profile("mongo")
internal class MongoTaskRepository(
    private val mongoOperations: MongoOperations
) : TaskRepository {
    override fun save(task: Task): Task {
        return mongoOperations.save(task)
    }

    override fun findById(id: String): Task {
        val mongoQuery = query(where("id").isEqualTo(id))
        return mongoOperations.findOne(mongoQuery, Task::class.java)
            ?: throw NoSuchElementException("Task with id $id not found")
    }

    override fun update(id: String, task: TaskUpdateRequest): Task {
        val mongoQuery = query(where("id").isEqualTo(id))
        val update = Update()
            .set("title", task.name)
            .set("description", task.description)
            .set("status", task.status)

        return mongoOperations.findAndModify(mongoQuery, update, Task::class.java)
            ?: throw NoSuchElementException("Task with id $id not found")
    }
}