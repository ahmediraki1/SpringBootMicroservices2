package com.example.showsMicroService.controller

import com.example.showsMicroService.exceptions.ResourceNotFoundException
import com.example.showsMicroService.model.ShowModel
import com.example.showsMicroService.repository.ShowsRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/shows")
class ShowsRestControler internal constructor(private val repository: ShowsRepository) {

    @GetMapping
    fun findAll(): List<ShowModel> {
        return repository.findAll()
    }

    @GetMapping(path = ["/{id}"])
    @Throws(ResourceNotFoundException::class)
    fun findById(@PathVariable id: Long): ResponseEntity<ShowModel> {
        return repository.findById(id)
                .map { record -> ResponseEntity.ok().body(record) }
                .orElseThrow { ResourceNotFoundException("Id doesn't exist : $id") }
    }

    @PostMapping
    fun createShow(@RequestBody showModel: ShowModel): ShowModel {
        return repository.save(showModel)
    }

    @PutMapping(path = ["/{id}"])
    @Throws(ResourceNotFoundException::class)
    fun updateShow(@PathVariable("id") id: Long,
                   @RequestBody showModel: ShowModel): ResponseEntity<ShowModel> {
        return repository.findById(id)
                .map { record ->
                    record.moviewName = showModel.moviewName
                    record.moviewYear = showModel.moviewYear
                    val updated = repository.save(record)
                    ResponseEntity.ok().body(updated)
                }.orElseThrow { ResourceNotFoundException("Id doesn't exist : $id") }
    }

    @DeleteMapping(path = ["/{id}"])
    @Throws(ResourceNotFoundException::class)
    fun deleteShow(@PathVariable("id") id: Long): ResponseEntity<*> {
        return repository.findById(id)
                .map { record ->
                    repository.deleteById(id)
                    ResponseEntity.ok().build<Any>()
                }.orElseThrow { ResourceNotFoundException("Id doesn't exist : $id") }
    }
}
