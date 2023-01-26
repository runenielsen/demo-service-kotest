package com.demoorg.demoservicekotest

import io.kotest.core.spec.style.StringSpec
import io.kotest.framework.concurrency.eventually
import io.kotest.matchers.shouldBe
import io.micronaut.rabbitmq.annotation.Binding
import io.micronaut.rabbitmq.annotation.Queue
import io.micronaut.rabbitmq.annotation.RabbitClient
import io.micronaut.rabbitmq.annotation.RabbitListener
import io.micronaut.test.extensions.kotest.annotation.MicronautTest

@MicronautTest
class EventTypeCountsServiceComponentTest(
    private val eventTypeCountsService: EventTypeCountsService,
    private val testQueueClient: TestQueueClient,
    private val testQueueListener: TestQueueListener
): StringSpec({

    "test update and get event type counts" {
        val event1 = Event("type1", "Some payload")
        val event2 = Event("type2", "Some other payload")
        testQueueClient.sendEvent(event1)
        testQueueClient.sendEvent(event1)
        testQueueClient.sendEvent(event1)
        testQueueClient.sendEvent(event2)

        @Suppress("OPT_IN_USAGE")
        eventually(5000) {
            val counts = eventTypeCountsService.listEventTypeCounts()

            counts.size shouldBe 2
            findCount(event1, counts).count shouldBe 3
            findCount(event2, counts).count shouldBe 1

            testQueueListener.outboundEventCount shouldBe 4
        }
    }
}) {

    @RabbitClient("demo-exchange")
    interface TestQueueClient {
        @Binding("event-queue1")
        fun sendEvent(event: Event)
    }

    @RabbitListener
    class TestQueueListener {

        var outboundEventCount = 0

        @Queue("event-queue2")
        fun receiveEvent() {
            outboundEventCount++
        }
    }
}

private fun findCount(event: Event, counts: List<EventTypeCount>): EventTypeCount {
    return counts
        .stream()
        .filter { eventTypeCount: EventTypeCount -> eventTypeCount.type == event.type }
        .findFirst()
        .orElseThrow { RuntimeException("Count not found!") }
}
