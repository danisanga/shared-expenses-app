package com.danisanga.shared.expenses.expenses.infrastructure.services.impl

import com.danisanga.shared.expenses.expenses.domain.entities.Expense
import com.danisanga.shared.expenses.expenses.domain.entities.Friend
import com.danisanga.shared.expenses.expenses.domain.entities.Party
import com.danisanga.shared.expenses.expenses.domain.exceptions.ExpenseException
import com.danisanga.shared.expenses.expenses.domain.repositories.ExpensesRepository
import com.danisanga.shared.expenses.expenses.domain.services.ExpensesService
import com.danisanga.shared.expenses.expenses.domain.services.FriendsService
import jakarta.inject.Singleton
import kotlin.jvm.Throws

@Singleton
class ExpensesServiceImpl(
        private val friendsService: FriendsService,
        private val expenseRepository: ExpensesRepository
) : ExpensesService {

    @Throws(ExpenseException::class)
    override fun createExpense(expense: Expense): Expense? {
        val friendsForParty = friendsService.getFriendsForParty(expense.party!!)
        if (friendsForParty != null && !friendsForParty.contains(expense.friend!!)) {
            throw ExpenseException("Expense cannot be added to this party")
        }
        return expenseRepository.save(expense)
    }

    override fun getExpensesForFriend(friend: Friend?): List<Expense>? {
        return expenseRepository.getExpensesForFriend(friend)
    }

    override fun getExpensesForParty(party: Party?): List<Expense>? {
        return expenseRepository.getExpensesForParty(party)
    }

}
