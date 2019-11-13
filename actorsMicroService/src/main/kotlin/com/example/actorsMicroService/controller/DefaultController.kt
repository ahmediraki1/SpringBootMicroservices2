package com.example.actorsMicroService.controller

import com.example.actorsMicroService.feign.ShowsFeignClient
import com.example.actorsMicroService.model.Show
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/")
class DefaultController {
    @GetMapping
    @Throws(Exception::class)
    fun pushError() {
        throw Exception("nothing to show here !")
    }

   
}
