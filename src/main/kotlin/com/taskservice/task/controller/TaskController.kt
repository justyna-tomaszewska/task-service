package com.taskservice.task.controller

import com.taskservice.task.service.TaskService
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/tasks")
class TaskController(
    val taskService: TaskService
) {

    @GetMapping(produces = [APPLICATION_JSON_VALUE])
    fun findTaskById(
        @RequestParam("id") id: String,
    ): ResponseEntity<*> {
        return ok(TaskDTO.from(taskService.findTaskById(id)))
    }
}