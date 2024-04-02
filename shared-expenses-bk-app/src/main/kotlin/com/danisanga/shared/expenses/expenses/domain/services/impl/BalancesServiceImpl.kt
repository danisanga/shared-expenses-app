package com.danisanga.shared.expenses.expenses.domain.services.impl

import com.danisanga.shared.expenses.expenses.domain.model.entities.Friend
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
    override fun getTotalBalance(partyId: UUID): TotalBalance {
        val party = partiesService.getPartyOrThrowException(partyId)
        val expensesForParty = expensesService.getExpensesForParty(party)
        val friendsForParty = friendsService.getFriendsForParty(party!!)
        val numberOfFriends = friendsForParty.size
        val totalSpent = expensesForParty.sumOf { it.quantity }

        val friendBalances = mutableListOf<FriendBalance>()
        for (uuid in friendsForParty) {
            val friend = friendsService.getFriendOrThrowException(uuid)
            val friendBalance = calculateFriendBalance(friend!!, totalSpent, numberOfFriends)
            friendBalances.add(friendBalance)
        }
        val totalBalance = TotalBalance(friendBalances)
        return totalBalance
    }

    private fun calculateFriendBalance(friend: Friend, totalSpent: Double, numberOfFriends: Int): FriendBalance {
        val expensesForFriend = expensesService.getExpensesForFriend(friend)

        val sumOf = expensesForFriend.sumOf { it.quantity }
        val sumOfBalance = getFriendBalance(sumOf, totalSpent, numberOfFriends)
        val friendBalance = FriendBalance(
                friend,
                Math.round(sumOfBalance * 100.0) / 100.0
        )
        return friendBalance
    }

    private fun getFriendBalance(balance: Double, totalSpent: Double, numberOfFriends: Int): Double {
        return balance - (totalSpent / numberOfFriends)
    }
}
