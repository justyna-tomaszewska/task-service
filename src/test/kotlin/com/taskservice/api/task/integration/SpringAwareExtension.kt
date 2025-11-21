package com.taskservice.api.task.integration

import io.kotest.core.listeners.BeforeSpecListener
import io.kotest.core.spec.Spec
import io.kotest.extensions.spring.testContextManager
import org.springframework.beans.BeansException
import org.springframework.context.ApplicationContext

abstract class SpringAwareExtension : BeforeSpecListener {

    lateinit var context: ApplicationContext

    override suspend fun beforeSpec(spec: Spec) {
        context = testContextManager().testContext.applicationContext
    }

    protected inline fun <reified T> getBean(): T =
        getBeanOrNull<T>()
            ?: error("Bean of type ${T::class.simpleName} is required to use ${this.javaClass.simpleName}")

    @Suppress("SwallowedException")
    protected inline fun <reified T> getBeanOrNull(): T? =
        try {
            context.getBean(T::class.java)
        } catch (e: BeansException) {
            null
        }
}