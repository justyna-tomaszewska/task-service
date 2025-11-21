package com.taskservice.api.task.integration

import com.taskservice.task.service.Task
import io.kotest.matchers.shouldBe
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus.ACCEPTED
import org.springframework.http.HttpStatus.OK

class TaskIntegrationTestWithRestExtSpec: BaseIntegrationTestSpec(){
    init{
        test("should save and return one user by id and timestamp") {
            // given
            val task = Task(
                id = "1",
                name = "Integration Test Task",
                description = "Created via integration test",
                status = "OPEN"
            )

            val response = taskRest.getTaskById<Task>(taskId = "1")
            // when: create the task
            response.statusCode shouldBe OK

        }
    }
}