package com.danisanga.shared.expenses.expenses.domain.services.impl

import com.danisanga.shared.expenses.expenses.domain.entities.Friend
import com.danisanga.shared.expenses.expenses.domain.model.FriendBalance
import com.danisanga.shared.expenses.expenses.domain.model.TotalBalance
import com.danisanga.shared.expenses.expenses.domain.services.BalancesService
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.util.*

@MicronautTest
class PendingPaymentsServiceImplTest {

    private val PARTY_UUID = UUID.randomUUID()
    private val FRIEND_UUID = UUID.randomUUID()
    private val OTHER_FRIEND_UUID = UUID.randomUUID()

    private val balancesService: BalancesService = mockk<BalancesService>()

    private val testObj = PendingPaymentsServiceImpl(balancesService)

    @Test
    fun `should return empty list when balances are empty`() {
        val totalBalanceStub = TotalBalance(
                emptyList()
        )
        every {
            balancesService.getTotalBalance(PARTY_UUID)
        } returns totalBalanceStub

        val result = testObj.getPendingPayments(PARTY_UUID)

        assert(result.isEmpty())
    }
    @Test
    fun `should return empty list when no debtors`() {
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
        val friendBalanceStub = FriendBalance(
                friendStub,
                10.0
        )
        val otherFriendBalanceStub = FriendBalance(
                otherFriendStub,
                10.0
        )
        val totalBalanceStub = TotalBalance(
                listOf(friendBalanceStub, otherFriendBalanceStub)
        )
        every {
            balancesService.getTotalBalance(PARTY_UUID)
        } returns totalBalanceStub

        val result = testObj.getPendingPayments(PARTY_UUID)

        assert(result.isEmpty())
    }

    @Test
    fun `should return pending payments for party with specific UUID`() {
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
        val friendBalanceStub = FriendBalance(
                friendStub,
                10.0
        )
        val otherFriendBalanceStub = FriendBalance(
                otherFriendStub,
                -10.0
        )
        val totalBalanceStub = TotalBalance(
                listOf(friendBalanceStub, otherFriendBalanceStub)
        )
        every {
            balancesService.getTotalBalance(PARTY_UUID)
        } returns totalBalanceStub

        val result = testObj.getPendingPayments(PARTY_UUID)

        assert(result.size == 1)
        assert(result[0].from.id == otherFriendStub.id)
        assert(result[0].to.id == friendStub.id)
        assert(result[0].quantity == 10.0)
    }

}