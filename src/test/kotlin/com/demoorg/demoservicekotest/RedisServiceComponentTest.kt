package com.demoorg.demoservicekotest

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.lettuce.core.api.StatefulRedisConnection
import io.lettuce.core.api.sync.RedisCommands
import io.micronaut.test.extensions.kotest.annotation.MicronautTest

@MicronautTest
class RedisServiceComponentTest(
    private val redisService: RedisService,
    private val statefulRedisConnection: StatefulRedisConnection<String, String>
): StringSpec({

    "test Redis" {
        val res = redisService.listEventTypeCounts()
        res shouldBe 1

        val syncCommands: RedisCommands<String, String> = statefulRedisConnection.sync()
        val value: String = syncCommands.get("mykey")
        value shouldBe "Hello from Lettuce!"
    }
})
