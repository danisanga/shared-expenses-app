package com.danisanga.shared.expenses.expenses.domain.services.impl

import com.danisanga.shared.expenses.expenses.domain.entities.Expense
import com.danisanga.shared.expenses.expenses.domain.entities.Friend
import com.danisanga.shared.expenses.expenses.domain.entities.Party
import com.danisanga.shared.expenses.expenses.infrastructure.repositories.ExpensesRepository
import com.danisanga.shared.expenses.expenses.domain.services.FriendsService
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.util.*

@MicronautTest
class ExpensesServiceImplTest {

    val FRIEND_UUID = UUID.randomUUID()
    val PARTY_UUID = UUID.randomUUID()
    val EXPENSE_UUID = UUID.randomUUID()

    private val friendsService: FriendsService = mockk<FriendsService>()
    private val expenseRepository: ExpensesRepository = mockk<ExpensesRepository>()

    private val testObj = ExpensesServiceImpl(friendsService, expenseRepository)

    @Test
    fun `should return expected expenses for friend`() {

        val friendStub = Friend(
                FRIEND_UUID,
                "friend_name",
                "friend_email",
                LocalDate.now(),
                null
        )
        val expenseStub = Expense(
                EXPENSE_UUID,
                10.05,
                "expense_desc",
                LocalDate.now(),
                null,
                friendStub
        )
        every {
            expenseRepository.getExpensesForFriend(friendStub)
        } returns listOf(expenseStub)

        val result = testObj.getExpensesForFriend(friendStub)

        assert(result.size == 1)
        assert(result[0] == expenseStub)
    }

    @Test
    fun `should return expected expenses for party`() {

        val partyStub = Party(
                PARTY_UUID,
                "party_name",
                LocalDate.now(),
                emptySet()
        )
        val expenseStub = Expense(
                EXPENSE_UUID,
                10.05,
                "expense_desc",
                LocalDate.now(),
                partyStub,
                null
        )
        every {
            expenseRepository.getExpensesForParty(partyStub)
        } returns listOf(expenseStub)

        val result = testObj.getExpensesForParty(partyStub)

        assert(result.size == 1)
        assert(result[0] == expenseStub)
    }

    @Test
    fun `should create a new expense`() {
        val partyStub = Party(
                PARTY_UUID,
                "party_name",
                LocalDate.now(),
                emptySet()
        )
        val friendStub = Friend(
                FRIEND_UUID,
                "friend_name",
                "friend_email",
                LocalDate.now(),
                partyStub
        )
        val expenseStub = Expense(
                EXPENSE_UUID,
                10.05,
                "expense_desc",
                LocalDate.now(),
                partyStub,
                friendStub
        )
        every {
            expenseRepository.save(expenseStub)
        } returns expenseStub
        every {
            friendsService.getFriendsForParty(partyStub)
        } returns listOf(FRIEND_UUID)

        val result = testObj.createExpense(expenseStub)

        assert(result?.quantity == 10.05)
        assert(result?.description == "expense_desc")
    }

}
