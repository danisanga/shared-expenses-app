package com.danisanga.shared.expenses.expenses.domain.services

import com.danisanga.shared.expenses.expenses.domain.entities.Expense
import com.danisanga.shared.expenses.expenses.domain.entities.Party
import java.util.*

interface ExpensesService {
    fun createExpense(expense: Expense): Expense?
}