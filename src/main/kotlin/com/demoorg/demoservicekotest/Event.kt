package com.demoorg.demoservicekotest

import io.micronaut.core.annotation.Creator
import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class Event @Creator constructor(val type: String, val payload: String)