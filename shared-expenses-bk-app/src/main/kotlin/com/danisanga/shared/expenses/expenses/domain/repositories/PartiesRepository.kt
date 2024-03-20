package com.danisanga.shared.expenses.expenses.domain.repositories

import com.danisanga.shared.expenses.expenses.domain.entities.Party
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository
import java.time.LocalDate
import java.util.*

@JdbcRepository(dialect = Dialect.POSTGRES)
interface PartiesRepository : CrudRepository<Party, UUID> {
    fun save(name: String, creationTime: LocalDate?): Party?
}