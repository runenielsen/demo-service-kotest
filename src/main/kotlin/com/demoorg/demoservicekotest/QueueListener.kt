package com.demoorg.demoservicekotest

import io.micronaut.rabbitmq.annotation.Queue
import io.micronaut.rabbitmq.annotation.RabbitListener

@RabbitListener
class QueueListener(private val eventTypeCountsService: EventTypeCountsService) {
    @Queue("event-queue1")
    fun receiveEvent(event: Event) {
        eventTypeCountsService.updateEventTypeCount(event)
    }
}
