package com.taskservice.api.task.integration

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.exchange
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod.GET
import org.springframework.http.ResponseEntity
import java.util.UUID.randomUUID

class TaskRestExtension: SpringAwareExtension() {
    val restTemplate: TestRestTemplate by lazy { getBean() }


    inline fun <reified T : Any> getTaskById(
        taskId: String
    ): ResponseEntity<T> = get("$TASK_PATH?id=$taskId")

    inline fun <reified T : Any> get(
        url: String
    ): ResponseEntity<T> =
        restTemplate.exchange(
            url,
            GET,
            HttpEntity(null, HttpHeaders().apply { set("x-request-id", randomUUID().toString()) })
        )

    companion object {
        const val TASK_PATH: String = "/tasks"
    }
}