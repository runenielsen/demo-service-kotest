package com.demoorg.demoservicekotest

import jakarta.inject.Singleton
import java.util.concurrent.ConcurrentHashMap

@Singleton
class EventTypeCountsService(private val queueClient: QueueClient) {
    private val eventTypeCounts: MutableMap<Event, Long> = ConcurrentHashMap()

    fun updateEventTypeCount(event: Event) {
        eventTypeCounts.compute(event) { _: Event, v: Long? ->
            if (v == null) 1L else v + 1
        }
        queueClient.sendEvent(Event("outbound", "Some outbound payload"))
    }

    fun listEventTypeCounts(): List<EventTypeCount> {
        return eventTypeCounts.map { (message, count): Map.Entry<Event, Long> ->
            EventTypeCount(message.type, count)
        }
    }
}