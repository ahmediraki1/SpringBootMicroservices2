package com.example.gateWay

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.netflix.zuul.EnableZuulProxy

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
open class GateWayApplication:SpringBootServletInitializer(){
	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			SpringApplication.run(GateWayApplication::class.java, *args)
		}
	}
}
