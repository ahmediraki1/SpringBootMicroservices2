package com.example.showsMicroService.controller

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
