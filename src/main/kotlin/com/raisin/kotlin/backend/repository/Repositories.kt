package com.raisin.kotlin.backend.repository

import com.raisin.kotlin.backend.model.Contact
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.transaction.annotation.Transactional

@Transactional
interface ContactRepository : JpaRepository<Contact, Int>