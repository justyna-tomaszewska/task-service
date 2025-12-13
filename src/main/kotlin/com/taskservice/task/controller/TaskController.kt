package com.taskservice.task.controller

import com.taskservice.task.service.Task
import com.taskservice.task.service.TaskService
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/tasks")
class TaskController(
    val taskService: TaskService
) {

    @GetMapping("{id}", produces = [APPLICATION_JSON_VALUE])
    fun findTaskById(
        @PathVariable("id") id: String,
    ): ResponseEntity<TaskDTO> {
        return ok(TaskDTO.from(taskService.findTaskById(id)))
    }

    @PostMapping(consumes = [APPLICATION_JSON_VALUE])
    fun createTask(@RequestBody payload: Task): ResponseEntity<*> {
        return ok(taskService.saveTask(payload))
    }

    @PatchMapping("/{id}", consumes = [APPLICATION_JSON_VALUE])
    fun updateTask(
        @PathVariable id: String,
        @RequestBody payload: TaskUpdateRequest
    ): ResponseEntity<Task> {
        val updatedTask = taskService.updateTask(id, payload)
        return ok(updatedTask)
    }
}