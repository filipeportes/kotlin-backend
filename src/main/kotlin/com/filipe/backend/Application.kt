package com.filipe.backend

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import javax.annotation.PostConstruct


@SpringBootApplication
class Application

@Autowired
lateinit var objectMapper: ObjectMapper

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}

@PostConstruct
fun setUp() {
    objectMapper.registerModule(JavaTimeModule())
}
