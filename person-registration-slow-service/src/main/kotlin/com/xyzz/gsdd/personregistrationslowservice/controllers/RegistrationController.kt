package com.xyzz.gsdd.personregistrationslowservice.controllers

import com.xyzz.gsdd.personregistrationslowservice.models.Person
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import java.time.Duration
import java.util.UUID

@RestController("/register")
class RegistrationController {

    @PostMapping("persons")
    @ResponseStatus(CREATED)
    fun register(@RequestBody person: Mono<Person>): Mono<Person> {
        return person.map { it.copy(id = UUID.randomUUID()) }
//        return person.delayElement(Duration.ofMillis(200)) // Mimic blocking nature
//                .map { it.copy(id = UUID.randomUUID()) }
    }

    @GetMapping
    @ResponseStatus(OK)
    fun registerGet(): Mono<Person>{
        return Mono.just(Person("f", "l", null))
    }
}
