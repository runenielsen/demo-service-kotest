package com.demoorg.demoservicekotest

import io.micronaut.rabbitmq.annotation.Binding
import io.micronaut.rabbitmq.annotation.RabbitClient

@RabbitClient("demo-exchange")
interface QueueClient {
    @Binding("event-queue2")
    fun sendEvent(event: Event)
}
