package com.danisanga.shared.expenses.expenses.domain.repositories

import com.danisanga.shared.expenses.expenses.domain.entities.Expense
import io.micronaut.data.exceptions.DataAccessException
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import java.util.*

@JdbcRepository(dialect = Dialect.POSTGRES)
abstract class ExpensesRepository : CrudRepository<Expense, UUID> {

    abstract fun save(@Valid expense: Expense) : Expense

    @Transactional
    open fun saveWithException(@Valid expense: Expense): Expense {
        save(expense)
        throw DataAccessException("test exception")
    }

    abstract fun update(@Valid expense: Expense) : Void
}