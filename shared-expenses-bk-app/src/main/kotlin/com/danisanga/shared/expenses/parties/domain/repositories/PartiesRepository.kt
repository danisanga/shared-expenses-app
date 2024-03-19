package com.danisanga.shared.expenses.parties.domain.repositories

import com.danisanga.shared.expenses.parties.domain.entities.Party
import io.micronaut.data.annotation.Id
import io.micronaut.data.exceptions.DataAccessException
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository
import jakarta.transaction.Transactional
import jakarta.validation.constraints.NotBlank
import java.time.LocalDate
import java.util.*

@JdbcRepository(dialect = Dialect.POSTGRES)
abstract class PartiesRepository : CrudRepository<Party, UUID> {

    abstract fun save(@NotBlank name: String, createdTime: LocalDate) : Party

    @Transactional
    open fun saveWithException(@NotBlank name: String, createdTime: LocalDate): Party {
        save(name, createdTime)
        throw DataAccessException("test exception")
    }

    abstract fun update(@Id id: UUID, @NotBlank name: String, createdTime: LocalDate) : Void
}