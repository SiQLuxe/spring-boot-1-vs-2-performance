package com.xyzz.gsdd.samplespringboot1.controllers

import com.xyzz.gsdd.samplespringboot1.models.Person
import com.xyzz.gsdd.samplespringboot1.services.PersonRegistrationService

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.CREATED
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono


@RestController("/persons")
class PersonController {
    @Autowired
    lateinit var personRegistrationService: PersonRegistrationService

//    @PostMapping
//    @ResponseStatus(CREATED)
//    fun addPerson(@RequestBody person: Person): Person {
//        return personRegistrationService.addPerson(person)
//    }

    @PostMapping
    @ResponseStatus(CREATED)
    fun addPerson(@RequestBody person: Person): Mono<Person> {
        return Mono.just(Person("L", "Q"))
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun personGet(): String{
        return "test...."
    }
}
