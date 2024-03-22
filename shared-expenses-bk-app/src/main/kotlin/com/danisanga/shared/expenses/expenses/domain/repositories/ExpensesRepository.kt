package com.danisanga.shared.expenses.expenses.domain.repositories

import com.danisanga.shared.expenses.expenses.domain.entities.Expense
import com.danisanga.shared.expenses.expenses.domain.entities.Friend
import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import java.util.*

@Repository
interface ExpensesRepository : CrudRepository<Expense, UUID> {

    @Query("FROM Expense b WHERE b.friend = :friendId")
    fun getExpensesForFriend(friendId: Friend?): List<Expense>

}