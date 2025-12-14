package com.taskservice.user.controller

import com.taskservice.user.service.User
import com.taskservice.user.service.UserService
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.logging.Logger

@RestController
@RequestMapping("/users")
class UserController(
    val userService: UserService
) {

    @GetMapping("{id}", produces = [APPLICATION_JSON_VALUE])
    fun findUserById(
        @PathVariable("id") id: String,
    ): ResponseEntity<UserDTO> {
        return ok(UserDTO.from(userService.findUserById(id)))
    }

    @PostMapping(consumes = [APPLICATION_JSON_VALUE])
    fun createUser(@RequestBody payload: User): ResponseEntity<*> {
        logger.info("Saving new user")
        return ok(userService.saveUser(payload))
    }

    @PatchMapping("/{id}", consumes = [APPLICATION_JSON_VALUE])
    fun updateUser(
        @PathVariable id: String,
        @RequestBody payload: UserUpdateRequest
    ): ResponseEntity<User> {
        val updatedUser = userService.updateUser(id, payload)
        return ok(updatedUser)
    }
    companion object {
        private val logger = Logger.getLogger(UserController::class.java.name)
    }
}

