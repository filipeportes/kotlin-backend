package com.filipe.backend.controller

import com.filipe.backend.model.Contact
import com.filipe.backend.repository.ContactRepository
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class ContactController {
    companion object: KLogging()

    @Autowired
    lateinit var contactRepository: ContactRepository

    @GetMapping("/contacts")
    fun contacts(): List<Contact> {
        return contactRepository.findAll()
    }

    @GetMapping("/contact/{id}")
    fun getContact(@PathVariable id: Int): Contact {
        return contactRepository.getOne(id)
    }

    @PostMapping("/contact", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createContact(@RequestBody contact: Contact) {
        contactRepository.save(contact)
    }

    @DeleteMapping("/contact/{id}")
    fun deleteContact(@PathVariable id: Int): ResponseEntity<Any> {
        contactRepository.deleteById(id)
        return ResponseEntity.ok("successfully deleted")
    }
}
