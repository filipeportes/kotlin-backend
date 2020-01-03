package com.raisin.kotlin.backend

import com.raisin.kotlin.backend.model.Contact
import com.raisin.kotlin.backend.repository.ContactRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate
    @Autowired
    lateinit var contactRepository: ContactRepository

    val contact = Contact(1, "Test", "test@mail.com", "12345678")

    @Test
    fun contextLoads() {
    }

    @Test
    fun `test simple endpoint` () {
        val result = testRestTemplate.getForEntity("/simple", String::class.java)

        assertNotNull(result)
        assertEquals(result.statusCode, HttpStatus.OK)
        assertEquals(result.body, "Hello, World!")
    }

    @Test
    fun `test contact operations` () {
        val result = testRestTemplate.postForEntity("/contact", contact, String::class.java)

        val savedContact = contactRepository.getOne(contact.id)
        assertNotNull(savedContact)
        assertEquals(contact, savedContact)
        assertEquals(result.statusCode, HttpStatus.OK)

        val list = testRestTemplate.getForObject("/contacts", ArrayList<Map<String, Any>>().javaClass)
        assertEquals(contactRepository.findAll().size,list.size)
        assertEquals(contact.email, list.first().get("email"))

        testRestTemplate.delete("/contact/${contact.id}")
        assertEquals(0, contactRepository.findAll().size)
    }
}
