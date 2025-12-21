package com.taskservice.caching

import com.hazelcast.config.Config
import com.hazelcast.config.MapConfig
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
        config.instanceName = "task-service-hazelcast-instance"

        val mapConfig = MapConfig()
        mapConfig.name = "tasks"
        mapConfig.timeToLiveSeconds = 3600 // Cache entries expire after 1 hour
        mapConfig.maxIdleSeconds = 1800 // Entries idle for 30 minutes will be evicted
        config.addMapConfig(mapConfig)

        return Hazelcast.newHazelcastInstance(config)
    }
}