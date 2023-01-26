package com.demoorg.demoservicekotest

import io.micronaut.runtime.EmbeddedApplication
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

@MicronautTest
class DemoServiceKotestTest(private val application: EmbeddedApplication<*>): StringSpec({

    "test the server is running" {
        application.isRunning shouldBe true
    }
})
