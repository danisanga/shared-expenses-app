package com.danisanga.shared.expenses.expenses.domain.services

import com.danisanga.shared.expenses.expenses.domain.model.entities.Expense
import com.danisanga.shared.expenses.expenses.domain.model.entities.Friend
import com.danisanga.shared.expenses.expenses.domain.model.entities.Party

interface ExpensesService {
    fun createExpense(expense: Expense): Expense?

    fun getExpensesForFriend(friend: Friend?): List<Expense>

    fun getExpensesForParty(party: Party?): List<Expense>
}