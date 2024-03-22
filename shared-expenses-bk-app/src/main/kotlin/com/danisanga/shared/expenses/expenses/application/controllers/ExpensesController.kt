package com.danisanga.shared.expenses.expenses.application.controllers

import com.danisanga.shared.expenses.expenses.application.dtos.CreateExpenseWsDTO
import com.danisanga.shared.expenses.expenses.application.dtos.ExpenseResponseWsDTO
import com.danisanga.shared.expenses.expenses.application.dtos.toDomain
import com.danisanga.shared.expenses.expenses.domain.entities.toApplication
import com.danisanga.shared.expenses.expenses.domain.services.ExpensesService
import com.danisanga.shared.expenses.expenses.domain.services.FriendsService
import com.danisanga.shared.expenses.expenses.domain.services.PartiesService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import jakarta.validation.constraints.NotBlank
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
    open fun getExpensesForFriend(@QueryValue @NotBlank friendId: UUID) : HttpResponse<ExpenseResponseWsDTO> {
        val friend = friendsService.getFriendOrThrowException(friendId)
        expensesService.getExpensesForFriend(friend)
        return HttpResponse.ok()
    }
}