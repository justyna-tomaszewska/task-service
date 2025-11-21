package com.taskservice.api.task

import com.taskservice.task.service.Task
import io.kotest.matchers.shouldBe

class TaskSpec : BaseSpec() {

    init {
        test("save new task should return saved task") {
            //given: We have a task to save
            val task = Task(
                id = "1",
                name = "Saved Task",
                description = "This is a saved task",
                status = "OPEN"
            )
            //when: We send a POST request to save the task
            val savedTask = taskService.saveTask(task)

            //then: We receive a 201 response with the saved task
            savedTask shouldBe task
        }
    }

}