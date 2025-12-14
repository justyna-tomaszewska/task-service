package com.taskservice.user.repository

import com.taskservice.user.controller.UserUpdateRequest
import com.taskservice.user.service.User
import org.springframework.context.annotation.Profile
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.query.Criteria.where
import org.springframework.data.mongodb.core.query.Query.query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.stereotype.Component

@Component
@Profile("mongo")
internal class MongoUserRepository(
    private val mongoOperations: MongoOperations
) : UserRepository {
    override fun save(user: User): User {
        return mongoOperations.save(user)
    }

    override fun findById(id: String): User {
        val mongoQuery = query(where("id").isEqualTo(id))
        return mongoOperations.findOne(mongoQuery, User::class.java)
            ?: throw NoSuchElementException("User with id $id not found")
    }

    override fun findByUsername(username: String): User? {
        val mongoQuery = query(where("username").isEqualTo(username))
        return mongoOperations.findOne(mongoQuery, User::class.java)
    }

    override fun update(id: String, user: UserUpdateRequest): User {
        val mongoQuery = query(where("id").isEqualTo(id))
        val update = Update()
            .set("username", user.username)
            .set("email", user.email)
            .set("role", user.role)

        return mongoOperations.findAndModify(mongoQuery, update, User::class.java)
            ?: throw NoSuchElementException("User with id $id not found")
    }
}

