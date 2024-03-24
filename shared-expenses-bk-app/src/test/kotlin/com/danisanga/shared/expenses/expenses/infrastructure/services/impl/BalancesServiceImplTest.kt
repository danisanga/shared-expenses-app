package com.danisanga.shared.expenses.expenses.infrastructure.services.impl

import com.danisanga.shared.expenses.expenses.domain.entities.Expense
import com.danisanga.shared.expenses.expenses.domain.entities.Friend
import com.danisanga.shared.expenses.expenses.domain.entities.Party
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
                null,
                emptySet()
        )
        val otherFriendStub = Friend(
                FRIEND_UUID,
                "other_friend_name",
                "other_friend_email",
                LocalDate.now(),
                null,
                emptySet()
        )
        val expenseStub = Expense(
                EXPENSE_UUID,
                10.05,
                "expense_desc",
                LocalDate.now(),
                null,
                friendStub
        )
        val partyStub = Party(
                PARTY_UUID,
                "party_name",
                LocalDate.now(),
                setOf(expenseStub),
                setOf(friendStub, otherFriendStub)
        )
        every {
            partiesService.getPartyOrThrowException(PARTY_UUID)
        } returns partyStub
        every {
            expenseService.getExpensesForParty(partyStub)
        } returns listOf(expenseStub)
        every {
            friendService.getFriendsForParty(partyStub)
        } returns listOf(friendStub, otherFriendStub)
        every {
            expenseService.getExpensesForFriend(friendStub)
        } returns listOf(expenseStub)
        every {
            expenseService.getExpensesForFriend(otherFriendStub)
        } returns listOf()

        val result = testObj.getTotalBalance(PARTY_UUID)

        assert(result?.friendBalances?.get(0)?.quantity == 5.025)
        assert(result?.friendBalances?.get(0)?.friend?.name == "friend_name")
        assert(result?.friendBalances?.get(1)?.quantity == -5.025)
        assert(result?.friendBalances?.get(1)?.friend?.name == "other_friend_name")

    }

}
