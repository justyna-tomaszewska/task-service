package com.taskservice.user.service

import com.taskservice.user.controller.UserUpdateRequest
import com.taskservice.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    val userRepository: UserRepository
) {
    fun findUserById(id: String): User {
        return userRepository.findById(id)
    }

    fun saveUser(user: User): User {
        return userRepository.save(user)
    }

    fun updateUser(id: String, user: UserUpdateRequest): User {
        return userRepository.update(id, user)
    }
}

