package com.danisanga.shared.expenses.expenses.infrastructure.services.impl

import com.danisanga.shared.expenses.expenses.domain.model.FriendBalance
import com.danisanga.shared.expenses.expenses.domain.model.TotalBalance
import com.danisanga.shared.expenses.expenses.domain.services.BalancesService
import com.danisanga.shared.expenses.expenses.domain.services.ExpensesService
import com.danisanga.shared.expenses.expenses.domain.services.FriendsService
import com.danisanga.shared.expenses.expenses.domain.services.PartiesService
import jakarta.inject.Singleton
import java.util.*

@Singleton
class BalancesServiceImpl(
        private val expensesService: ExpensesService,
        private val partiesService: PartiesService,
        private val friendsService: FriendsService
): BalancesService {
    override fun getTotalBalance(partyId: UUID): TotalBalance? {
        val party = partiesService.getPartyOrThrowException(partyId)
        val expensesForParty = expensesService.getExpensesForParty(party)
        val friendsForParty = friendsService.getFriendsForParty(party!!)
        val numberOfFriends = friendsForParty?.size

        var totalSpent = 0.0
        if (expensesForParty != null) {
            totalSpent = expensesForParty.sumOf { it.quantity }
        }

        if (friendsForParty != null) {
            val balances = mutableListOf<FriendBalance>()
            for (friend in friendsForParty) {
                val expensesForFriend = expensesService.getExpensesForFriend(friend)

                var sumOfBalance = 0.0
                if (expensesForFriend != null) {
                    val sumOf = expensesForFriend.sumOf { it.quantity }
                    sumOfBalance = getFriendBalance(sumOf, totalSpent, numberOfFriends!!)
                }
                val friendBalance = FriendBalance(
                        friend,
                        sumOfBalance
                )
                balances.add(friendBalance)
            }
            val totalBalance = TotalBalance(balances)
            return totalBalance
        }
        return null
    }

    private fun getFriendBalance(balance: Double, totalSpent: Double, numberOfFriends: Int): Double {
        return balance - (totalSpent / numberOfFriends)
    }
}