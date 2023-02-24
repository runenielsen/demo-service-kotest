package com.demoorg.demoservicekotest

import io.lettuce.core.api.StatefulRedisConnection
import io.lettuce.core.api.sync.RedisCommands
import jakarta.inject.Singleton

@Singleton
class RedisService(private val statefulRedisConnection: StatefulRedisConnection<String, String>) {

    fun listEventTypeCounts(): Int {

        val syncCommands: RedisCommands<String, String> = statefulRedisConnection.sync()
        syncCommands.set("mykey", "Hello from Lettuce!")
        return 1
    }
}