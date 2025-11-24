package com.taskservice.api.task.unit

import com.taskservice.task.service.TaskService
import io.kotest.core.spec.style.FunSpec

abstract class BaseSpec(body: FunSpec.() -> Unit = {}) : FunSpec(body){
    val taskRepository = TestTaskRepository()
    val taskService = TaskService(taskRepository)
    init {
        assertSoftly = true
    }
}