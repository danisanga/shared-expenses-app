package com.danisanga.shared.expenses.expenses.domain.services.impl

import com.danisanga.shared.expenses.expenses.domain.entities.Expense
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

}
