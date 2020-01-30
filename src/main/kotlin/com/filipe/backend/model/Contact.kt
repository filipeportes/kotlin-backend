package com.filipe.backend.model

import java.time.LocalDate
import javax.persistence.*

@Entity
data class Contact (
        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        val id: Int,
        @Column(nullable = false)
        val name: String,
        val email: String,
        val phone: String,
        val creationDate: LocalDate = LocalDate.now()
)
