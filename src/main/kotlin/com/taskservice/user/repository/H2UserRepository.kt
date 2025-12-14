package com.taskservice.user.repository

import com.taskservice.user.controller.UserUpdateRequest
import com.taskservice.user.service.User
import org.springframework.context.annotation.Profile
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

interface UserJpaRepository : JpaRepository<User, String> {
    fun findByUsername(username: String): User?
}

@Repository
@Profile("!mongo")
class H2UserRepository(
    private val jpaRepository: UserJpaRepository
) : UserRepository {
    override fun save(user: User): User {
        return jpaRepository.save(user)
    }

    override fun findById(id: String): User {
        return jpaRepository.findById(id).orElseThrow { NoSuchElementException("User not found") }
    }

    override fun findByUsername(username: String): User? {
        return jpaRepository.findByUsername(username)
    }

    override fun update(
        id: String,
        user: UserUpdateRequest
    ): User {
        val existingUser = jpaRepository.findById(id).orElseThrow { NoSuchElementException("User not found") }
        val updatedUser = existingUser.copy(
            username = user.username?: existingUser.username,
            email = user.email?: existingUser.email,
            role = user.role?: existingUser.role
        )
        return jpaRepository.save(updatedUser)
    }

}

