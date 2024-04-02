package com.danisanga.shared.expenses.expenses.domain.services.impl

import com.danisanga.shared.expenses.expenses.domain.model.entities.Expense
import com.danisanga.shared.expenses.expenses.domain.model.entities.Friend
import com.danisanga.shared.expenses.expenses.domain.model.entities.Party
import com.danisanga.shared.expenses.expenses.domain.services.ExpensesService
import com.danisanga.shared.expenses.expenses.domain.services.FriendsService
import com.danisanga.shared.expenses.expenses.domain.services.PartiesService
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.util.*

@MicronautTest
class BalancesServiceImplTest {
    val PARTY_UUID = UUID.randomUUID()
    val FRIEND_UUID = UUID.randomUUID()
    val OTHER_FRIEND_UUID = UUID.randomUUID()
    val EXPENSE_UUID = UUID.randomUUID()

    private val expenseService: ExpensesService = mockk<ExpensesService>()
    private val partiesService: PartiesService = mockk<PartiesService>()
    private val friendService: FriendsService = mockk<FriendsService>()

    private val testObj = BalancesServiceImpl(expenseService, partiesService, friendService)

    @Test
    fun `should calculate party balance`() {

        val friendStub = Friend(
                FRIEND_UUID,
                "friend_name",
                "friend_email",
                LocalDate.now(),
                null
        )
        val otherFriendStub = Friend(
                OTHER_FRIEND_UUID,
                "other_friend_name",
                "other_friend_email",
                LocalDate.now(),
                null
        )
        val expenseStub = Expense(
            EXPENSE_UUID,
            10.0,
            "expense_desc",
            null,
            friendStub
        )
        val partyStub = Party(
                PARTY_UUID,
                "party_name",
                LocalDate.now(),
                setOf(expenseStub)
        )
        every {
            partiesService.getPartyOrThrowException(PARTY_UUID)
        } returns partyStub
        every {
            expenseService.getExpensesForParty(partyStub)
        } returns listOf(expenseStub)
        every {
            friendService.getFriendsForParty(partyStub)
        } returns listOf(FRIEND_UUID, OTHER_FRIEND_UUID)
        every {
            expenseService.getExpensesForFriend(friendStub)
        } returns listOf(expenseStub)
        every {
            expenseService.getExpensesForFriend(otherFriendStub)
        } returns listOf()
        every {
            friendService.getFriendOrThrowException(FRIEND_UUID)
        } returns friendStub
        every {
            friendService.getFriendOrThrowException(OTHER_FRIEND_UUID)
        } returns otherFriendStub

        val result = testObj.getTotalBalance(PARTY_UUID)

        assert(result.friendBalances[0].quantity == 5.0)
        assert(result.friendBalances[0].friend.id == FRIEND_UUID)
        assert(result.friendBalances[0].friend.name == "friend_name")
        assert(result.friendBalances[1].quantity == -5.0)
        assert(result.friendBalances[1].friend.id == OTHER_FRIEND_UUID)
        assert(result.friendBalances[1].friend.name == "other_friend_name")

    }

}
