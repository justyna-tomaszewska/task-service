package com.taskservice

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class TaskServiceApplication

fun main(args: Array<String>) {

    val context = runApplication<TaskServiceApplication>(*args)
    val logger = LoggerFactory.getLogger(TaskServiceApplication::class.java)
    val requestMappingHandlerMapping = context.getBean(RequestMappingHandlerMapping::class.java)

    logger.info("=== Registered Endpoints ===")
    requestMappingHandlerMapping.handlerMethods.forEach { (key, value) ->
        logger.info("${key.methodsCondition} ${key.patternsCondition} -> ${value.beanType.simpleName}.${value.method.name}")
    }
    logger.info("============================")

}

