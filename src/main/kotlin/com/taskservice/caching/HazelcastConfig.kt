package com.taskservice.caching

import com.hazelcast.config.Config
import com.hazelcast.core.Hazelcast
import com.hazelcast.core.HazelcastInstance
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableCaching
class HazelcastConfig {

    @Bean
    fun hazelcastInstance(): HazelcastInstance {
        val config = Config()
        config.clusterName = "dev"
        return Hazelcast.newHazelcastInstance(config)
    }
}