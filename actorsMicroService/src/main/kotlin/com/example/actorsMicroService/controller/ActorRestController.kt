package com.example.actorsMicroService.controller

import com.example.actorsMicroService.exceptions.ResourceNotFoundException
import com.example.actorsMicroService.model.ActorModel
import com.example.actorsMicroService.repository.ActorsRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import com.example.actorsMicroService.feign.ShowsFeignClient
import com.example.actorsMicroService.model.Show
import java.util.HashMap

@RestController
@RequestMapping("/actors")
class ActorRestController internal constructor(
	private val repository: ActorsRepository,
	private val showsServiceClient: ShowsFeignClient
) {

	@GetMapping
	fun findAll(): List<ActorModel> {
		return repository.findAll()
	}

	@GetMapping(path = ["/{id}"])
	@Throws(ResourceNotFoundException::class)
	fun findById(@PathVariable id: Long): ResponseEntity<ActorModel> {
		return repository.findById(id)
			.map { record -> ResponseEntity.ok().body(record) }
			.orElseThrow { ResourceNotFoundException("Id doesn't exist : $id") }
	}

	@PostMapping
	fun createActor(@RequestBody actorModel: ActorModel): ActorModel {
		return repository.save(actorModel)
	}

	@PutMapping(path = ["/{id}"])
	@Throws(ResourceNotFoundException::class)
	fun updateActor(
		@PathVariable("id") id: Long,
		@RequestBody actorModel: ActorModel
	): ResponseEntity<ActorModel> {
		return repository.findById(id)
			.map { record ->
				record.actorName = actorModel.actorName
				record.actorEmail = actorModel.actorEmail
				record.actorPhone = actorModel.actorPhone
				val updated = repository.save(record)
				ResponseEntity.ok().body(updated)
			}.orElseThrow { ResourceNotFoundException("Id doesn't exist : $id") }
	}

	@DeleteMapping(path = ["/{id}"])
	@Throws(ResourceNotFoundException::class)
	fun deleteActor(@PathVariable("id") id: Long): ResponseEntity<*> {
		return repository.findById(id)
			.map { record ->
				repository.deleteById(id)
				ResponseEntity.ok().build<Any>()
			}.orElseThrow { ResourceNotFoundException("Id doesn't exist : $id") }
	}

	@GetMapping("/testMicroservicesConnectivity1")
	@Throws(Exception::class)
	fun combineAll(): HashMap<String, Any> {
		val hashmap = HashMap<String, Any>()
		hashmap.put("shows", showsServiceClient.getShows())
		hashmap.put("actors", repository.findAll())
		return hashmap

	}

	@RequestMapping(method = [RequestMethod.GET], value = ["/testMicroservicesConnectivity2/{id}/{movieId}"])
	@Throws(ResourceNotFoundException::class)
	fun combineFindById(@PathVariable id: Long, @PathVariable movieId: Long): ResponseEntity<HashMap<String, Any>> {
		val hashmap = HashMap<String, Any>()
		val show = showsServiceClient.getShowById(movieId)
		return repository.findById(id)
			.map { record ->
				hashmap.put("show", show)
				hashmap.put("actor", record)
				ResponseEntity.ok().body(hashmap)
			}
			.orElseThrow { ResourceNotFoundException("Actor doesn't exist : $id") }
	}
}
