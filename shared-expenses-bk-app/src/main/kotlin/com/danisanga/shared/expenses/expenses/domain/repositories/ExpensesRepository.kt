package com.danisanga.shared.expenses.expenses.domain.repositories

import com.danisanga.shared.expenses.expenses.domain.entities.Expense
import io.micronaut.data.annotation.Join
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import java.util.*

@Repository
interface ExpensesRepository : CrudRepository<Expense, UUID> {

//    @Join(value = "party", type = Join.Type.FETCH)
//    fun list(): List<Expense>
}