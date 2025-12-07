package com.taskservice.api.task.integration

import com.taskservice.task.TaskServiceApplication
import com.taskservice.task.repository.MongoTaskRepository
import io.kotest.core.spec.style.FunSpec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.test.context.ActiveProfiles
import io.kotest.extensions.spring.SpringExtension
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.query.Query
import org.springframework.test.context.ContextConfiguration

@SpringBootTest(
    classes = [TaskServiceApplication::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles("integration")
@ContextConfiguration(initializers = [MongoDbInitializer::class])
class BaseIntegrationTestSpec(body: FunSpec.() -> Unit = {}) : FunSpec(body) {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Autowired
    lateinit var mongoOperations: MongoOperations

    init {
        extensions(SpringExtension())
        assertSoftly = true
        beforeAny {
            mongoOperations.collectionNames.forEach { collection -> mongoOperations.remove(Query(), collection) }
        }
    }
}