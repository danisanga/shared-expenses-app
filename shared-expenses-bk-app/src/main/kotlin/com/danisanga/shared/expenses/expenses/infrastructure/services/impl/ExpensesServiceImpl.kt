package com.danisanga.shared.expenses.expenses.infrastructure.services.impl

import com.danisanga.shared.expenses.expenses.domain.entities.Expense
import com.danisanga.shared.expenses.expenses.domain.entities.Friend
import com.danisanga.shared.expenses.expenses.domain.repositories.ExpensesRepository
import com.danisanga.shared.expenses.expenses.domain.services.ExpensesService
import jakarta.inject.Singleton
import java.util.*

@Singleton
class ExpensesServiceImpl (
        private val expenseRepository: ExpensesRepository
): ExpensesService {
    override fun createExpense(expense: Expense): Expense? {
        return expenseRepository.save(expense)
    }

    override fun getExpensesForFriend(friendId: Friend?): List<Expense>? {
        return expenseRepository.getExpensesForFriend(friendId)
    }

}
