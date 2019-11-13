package com.example.showsMicroService

import com.example.showsMicroService.model.ShowModel
import com.example.showsMicroService.repository.ShowsRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import java.util.stream.LongStream

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
open class ShowsMicroServiceApplication:SpringBootServletInitializer(){
	@Bean
	open fun initActors(repository: ShowsRepository)= CommandLineRunner {
		repository.deleteAll()
		LongStream.range(1, 11)
				.mapToObj { i ->
					val c = ShowModel()
					c.moviewName = "Movie $i"
					c.moviewYear = (2010+i).toString()
					c
				}
				.map { v -> repository.save(v) }
				.forEach({ println(it) })
	}

	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			SpringApplication.run(ShowsMicroServiceApplication::class.java, *args)
		}
	}

}
