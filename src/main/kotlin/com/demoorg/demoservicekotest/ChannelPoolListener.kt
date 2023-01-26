package com.demoorg.demoservicekotest

import com.rabbitmq.client.BuiltinExchangeType
import com.rabbitmq.client.Channel
import io.micronaut.rabbitmq.connect.ChannelInitializer
import jakarta.inject.Singleton
import java.io.IOException

@Singleton
class ChannelPoolListener : ChannelInitializer() {
    @Throws(IOException::class)
    override fun initialize(channel: Channel, name: String) {
        channel.exchangeDeclare("demo-exchange", BuiltinExchangeType.DIRECT, true)
        channel.queueDeclare("event-queue1", true, false, false, null)
        channel.queueBind("event-queue1", "demo-exchange", "event-queue1")
        channel.queueDeclare("event-queue2", true, false, false, null)
        channel.queueBind("event-queue2", "demo-exchange", "event-queue2")
    }
}