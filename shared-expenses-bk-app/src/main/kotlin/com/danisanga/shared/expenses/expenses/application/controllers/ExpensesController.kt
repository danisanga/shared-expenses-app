package com.danisanga.shared.expenses.expenses.application.controllers

import com.danisanga.shared.expenses.expenses.application.dtos.CreateExpenseWsDTO
import com.danisanga.shared.expenses.expenses.application.dtos.ExpenseResponseWsDTO
import com.danisanga.shared.expenses.expenses.application.dtos.toDomain
import com.danisanga.shared.expenses.expenses.domain.entities.Expense
import com.danisanga.shared.expenses.expenses.domain.entities.toApplication
import com.danisanga.shared.expenses.expenses.domain.services.ExpensesService
import com.danisanga.shared.expenses.expenses.domain.services.FriendsService
import com.danisanga.shared.expenses.expenses.domain.services.PartiesService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import jakarta.validation.constraints.NotNull
import java.util.*

@ExecuteOn(TaskExecutors.BLOCKING)
@Controller("/expenses")
open class ExpensesController(
        private val expensesService: ExpensesService,
        private val partiesService: PartiesService,
        private val friendsService: FriendsService
) {

    @Post("/create")
    open fun createExpense(@Body expense: CreateExpenseWsDTO) : HttpResponse<ExpenseResponseWsDTO> {
        val expenseDomain = expense.toDomain()
        expenseDomain.friend = friendsService.getFriendOrThrowException(expense.friend);
        expenseDomain.party = partiesService.getPartyOrThrowException(expense.party);
        expensesService.createExpense(expenseDomain)
        return HttpResponse
                .created(expenseDomain.toApplication())
    }

    @Get("/friend/{friendId}")
    open fun getExpensesForFriend(@QueryValue @NotNull friendId: UUID) : HttpResponse<ExpenseResponseWsDTO> {
        val friend = friendsService.getFriendOrThrowException(friendId)
        expensesService.getExpensesForFriend(friend)
        return HttpResponse.ok()
    }

    @Get("/party/{partyId}")
    open fun getExpensesForParty(@QueryValue @NotNull partyId: UUID) : HttpResponse<List<ExpenseResponseWsDTO>> {
        val party = partiesService.getPartyOrThrowException(partyId)
        val expensesForParty = expensesService.getExpensesForParty(party)
        val expensesApplication = convertExpensesToPartyExpenses(expensesForParty)
        return HttpResponse.ok(expensesApplication)
    }

    private fun convertExpensesToPartyExpenses(expenses: List<Expense>?): List<ExpenseResponseWsDTO> {
        return if (expenses.isNullOrEmpty()) {
          emptyList()
        } else {
            expenses.map { expense ->
                ExpenseResponseWsDTO(
                        expense.id,
                        expense.quantity,
                        expense.friend
                )
            }
        }
    }
}
