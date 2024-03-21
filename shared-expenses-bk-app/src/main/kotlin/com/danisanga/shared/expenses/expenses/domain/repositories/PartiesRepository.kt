package com.danisanga.shared.expenses.expenses.domain.repositories

import com.danisanga.shared.expenses.expenses.domain.entities.Party
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import jakarta.transaction.Transactional
import java.time.LocalDate
import java.util.*

@Repository
interface PartiesRepository : CrudRepository<Party, UUID> {

    @Transactional
    fun save(name: String, creationTime: LocalDate?): Party?
}