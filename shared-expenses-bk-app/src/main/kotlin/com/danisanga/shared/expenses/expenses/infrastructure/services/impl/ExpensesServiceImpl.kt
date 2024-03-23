package com.danisanga.shared.expenses.expenses.infrastructure.services.impl

import com.danisanga.shared.expenses.expenses.domain.entities.Expense
import com.danisanga.shared.expenses.expenses.domain.entities.Friend
import com.danisanga.shared.expenses.expenses.domain.entities.Party
import com.danisanga.shared.expenses.expenses.domain.repositories.ExpensesRepository
import com.danisanga.shared.expenses.expenses.domain.services.ExpensesService
import jakarta.inject.Singleton

@Singleton
class ExpensesServiceImpl (
        private val expenseRepository: ExpensesRepository
): ExpensesService {
    override fun createExpense(expense: Expense): Expense? {
        return expenseRepository.save(expense)
    }

    override fun getExpensesForFriend(friend: Friend?): List<Expense>? {
        return expenseRepository.getExpensesForFriend(friend)
    }

    override fun getExpensesForParty(party: Party?): List<Expense>? {
        return expenseRepository.getExpensesForParty(party)
    }

}
