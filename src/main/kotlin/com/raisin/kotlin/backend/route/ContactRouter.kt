package com.raisin.kotlin.backend.route

import com.fasterxml.jackson.databind.ObjectMapper
import com.raisin.kotlin.backend.model.Contact
import com.raisin.kotlin.backend.repository.ContactRepository
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.component.jackson.ListJacksonDataFormat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


@Component
class ContactRouter @Autowired constructor (
        val contactRepository: ContactRepository,
        val objectMapper: ObjectMapper ) : RouteBuilder() {

    override fun configure() {
        from("file:/tmp/contacts/?moveFailed=.failed&move=.done")
                .unmarshal(ListJacksonDataFormat(objectMapper, Contact::class.java))
                .process { exchange ->
                    val contacts = exchange.getIn().getBody(ArrayList<Contact>().javaClass)
                    contactRepository.saveAll(contacts)
                }

    }
}