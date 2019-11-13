package com.example.actorsMicroService.feign

import feign.Logger
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class ShowsFeignClentConfig {
    @Bean
    open fun feignLoggerLevel(): Logger.Level {
        return Logger.Level.HEADERS
    }
}
