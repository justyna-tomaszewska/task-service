package com.taskservice.user.repository

import com.taskservice.user.controller.UserUpdateRequest
import com.taskservice.user.service.User

interface UserRepository{
    fun save(user: User): User
    fun findById(id: String): User
    fun findByUsername(username: String): User?
    fun update(id: String, user: UserUpdateRequest): User
}

