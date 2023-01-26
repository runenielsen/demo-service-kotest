package com.demoorg.demoservicekotest

import io.micronaut.core.annotation.Creator
import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class EventTypeCount @Creator constructor(val type: String, val count: Long)