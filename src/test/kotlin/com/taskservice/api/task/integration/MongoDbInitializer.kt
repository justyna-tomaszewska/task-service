package com.taskservice.api.task.integration

import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.testcontainers.containers.MongoDBContainer

/**
 * Does 2 things:
 * * starts mongo test container (fetched from Allegro infra)
 * * runs provisioning scripts
 */
class MongoDbInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {

    override fun initialize(applicationContext: ConfigurableApplicationContext) {
        val mongoDBContainer = MongoDBContainer("mongo:7.0")
            .apply { start() }

        mongoDBContainer.start()

        TestPropertyValues.of("spring.data.mongodb.uri=${mongoDBContainer.connectionString}/test")
            .applyTo(applicationContext)
        TestPropertyValues.of("scheduler.test.mongo.uri=${mongoDBContainer.connectionString}/test")
            .applyTo(applicationContext)

    }
}
