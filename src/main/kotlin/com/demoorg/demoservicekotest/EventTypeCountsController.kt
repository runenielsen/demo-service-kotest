package com.demoorg.demoservicekotest

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/event-type-counts")
class EventTypeCountsController(private val eventTypeCountsService: EventTypeCountsService) {

    @Get
    fun listEventTypeCounts(): List<EventTypeCount> {
        return eventTypeCountsService.listEventTypeCounts()
    }
}