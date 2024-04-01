package com.danisanga.shared.expenses.expenses.domain.services.impl

import com.danisanga.shared.expenses.expenses.domain.entities.Expense
import com.danisanga.shared.expenses.expenses.domain.entities.Friend
import com.danisanga.shared.expenses.expenses.domain.entities.Party
import com.danisanga.shared.expenses.expenses.domain.exceptions.ExpenseException
import com.danisanga.shared.expenses.expenses.infrastructure.repositories.ExpensesRepository
import com.danisanga.shared.expenses.expenses.domain.services.ExpensesService
import com.danisanga.shared.expenses.expenses.domain.services.FriendsService
import jakarta.inject.Singleton

@Singleton
class ExpensesServiceImpl(
        private val friendsService: FriendsService,
        private val expenseRepository: ExpensesRepository
) : ExpensesService {

    @Throws(ExpenseException::class)
    override fun createExpense(expense: Expense): Expense? {
        val friendsForParty = friendsService.getFriendsForParty(expense.party!!)
        if (!friendsForParty.contains(expense.friend?.id)) {
            throw ExpenseException("Expense cannot be added to this party")
        }
        return expenseRepository.save(expense)
    }

    override fun getExpensesForFriend(friend: Friend?): List<Expense> {
        return expenseRepository.getExpensesForFriend(friend)
    }

    override fun getExpensesForParty(party: Party?): List<Expense> {
        return expenseRepository.getExpensesForParty(party)
    }

}
