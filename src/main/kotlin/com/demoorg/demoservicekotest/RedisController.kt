package com.demoorg.demoservicekotest

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/redis")
class RedisController(private val redisService: RedisService) {

    @Get
    fun redis(): Int {
        return redisService.listEventTypeCounts()
    }
}