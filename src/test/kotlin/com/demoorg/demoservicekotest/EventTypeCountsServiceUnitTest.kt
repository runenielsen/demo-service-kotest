package com.demoorg.demoservicekotest

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.mockk

class EventTypeCountsServiceUnitTest: StringSpec({

    val eventTypeCountsService = EventTypeCountsService(mockk(relaxed = true))

    "test update and get event type counts" {
        val event1 = Event("type1", "Some payload")
        val event2 = Event("type2", "Some other payload")
        eventTypeCountsService.updateEventTypeCount(event1)
        eventTypeCountsService.updateEventTypeCount(event1)
        eventTypeCountsService.updateEventTypeCount(event1)
        eventTypeCountsService.updateEventTypeCount(event2)
        val counts = eventTypeCountsService.listEventTypeCounts()

        counts.size shouldBe 2
        findCount(event1, counts).count shouldBe 3
        findCount(event2, counts).count shouldBe 1
    }
})

private fun findCount(event: Event, counts: List<EventTypeCount>): EventTypeCount {
    return counts
        .stream()
        .filter { eventTypeCount: EventTypeCount -> eventTypeCount.type == event.type }
        .findFirst()
        .orElseThrow { RuntimeException("Count not found!") }
}
