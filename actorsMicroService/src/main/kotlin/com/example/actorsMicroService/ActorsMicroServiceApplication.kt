package com.example.actorsMicroService

import com.example.actorsMicroService.model.ActorModel
import com.example.actorsMicroService.repository.ActorsRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import java.util.stream.LongStream

@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
open class ActorsMicroServiceApplication : SpringBootServletInitializer() {
    // bean to insert some dummy data inside the actors table
    @Bean
    open fun initActors(repository: ActorsRepository) = CommandLineRunner {
        repository.deleteAll()
        LongStream.range(1, 11)
                .mapToObj { i ->
                    val c = ActorModel()
                    c.actorName = "ActorModel $i"
                    c.actorEmail = "ActorModel$i@email.com"
                    c.actorPhone = "+20100" + i * 100 + "8" + i
                    c
                }
                .map {
                    v -> repository.save(v)
                }
                .forEach({ println(it) })
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(ActorsMicroServiceApplication::class.java, *args)
        }
    }

}
