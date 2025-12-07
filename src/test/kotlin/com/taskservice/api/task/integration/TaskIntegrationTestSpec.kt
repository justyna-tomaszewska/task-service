package com.taskservice.api.task.integration

import com.taskservice.task.controller.TaskDTO
import com.taskservice.task.service.Task
import io.kotest.matchers.shouldBe
import org.springframework.http.HttpStatus.OK

class TaskIntegrationTestSpec: BaseIntegrationTestSpec(){
    init{
        test("should save and return one user by id and timestamp") {
            // given
            val task = Task(
                id = "1",
                name = "Integration Test Task",
                description = "Created via integration test",
                status = "OPEN"
            )
            // when: create the task
            val createResponse = restTemplate.postForEntity("/tasks", task, Task::class.java)
            createResponse.statusCode shouldBe OK

            // when: retrieve the task
            val getResponse = restTemplate.getForEntity("/tasks/1", TaskDTO::class.java)
            getResponse.statusCode shouldBe OK
            getResponse.body?.name shouldBe "Integration Test Task"
            getResponse.body?.description shouldBe "Created via integration test"
            getResponse.body?.status shouldBe "OPEN"
        }
    }
}