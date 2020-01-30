package com.filipe.backend.repository

import com.filipe.backend.model.Contact
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.transaction.annotation.Transactional

@Transactional
interface ContactRepository : JpaRepository<Contact, Int>