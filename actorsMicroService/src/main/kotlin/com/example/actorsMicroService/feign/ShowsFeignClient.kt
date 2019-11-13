package com.example.actorsMicroService.feign

import com.example.actorsMicroService.model.Show
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable


@FeignClient(
	name = "showsMicroService",
	url = "http://localhost:8805/api/showsService",
	configuration = [ShowsFeignClentConfig::class]
)
interface ShowsFeignClient {
	@GetMapping("/shows")
	fun getShows(): List<Show>

	@RequestMapping(value=["/shows/{id}"])
	fun getShowById(@PathVariable id: Long ): Show

}
