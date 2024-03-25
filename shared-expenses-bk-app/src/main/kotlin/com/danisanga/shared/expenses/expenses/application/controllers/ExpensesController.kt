package com.danisanga.shared.expenses.expenses.application.controllers

import com.danisanga.shared.expenses.expenses.application.dtos.CreateExpenseWsDTO
import com.danisanga.shared.expenses.expenses.application.dtos.ErrorWsDTO
import com.danisanga.shared.expenses.expenses.application.dtos.ExpenseResponseWsDTO
import com.danisanga.shared.expenses.expenses.domain.converters.ExpensesConverter
import com.danisanga.shared.expenses.expenses.domain.entities.Expense
import com.danisanga.shared.expenses.expenses.domain.exceptions.ExpenseException
import com.danisanga.shared.expenses.expenses.domain.services.ExpensesService
import com.danisanga.shared.expenses.expenses.domain.services.FriendsService
import com.danisanga.shared.expenses.expenses.domain.services.PartiesService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import java.util.*

@ExecuteOn(TaskExecutors.BLOCKING)
@Controller("/expenses")
open class ExpensesController(
        private val expensesService: ExpensesService,
        private val partiesService: PartiesService,
        private val friendsService: FriendsService,
        private val expensesConverter: ExpensesConverter
) {

    @Post("/create")
    open fun createExpense(@Body @Valid createExpenseWsDTO: CreateExpenseWsDTO) : HttpResponse<ExpenseResponseWsDTO> {
        val expense = expensesConverter.convertToDomain(createExpenseWsDTO)
        expensesService.createExpense(expense)
        return HttpResponse
                .created(expensesConverter.convertToApplication(expense))
    }

    @Get("/friend/{friendId}")
    open fun getExpensesForFriend(@QueryValue @NotNull friendId: UUID) : HttpResponse<List<ExpenseResponseWsDTO>> {
        val friend = friendsService.getFriendOrThrowException(friendId)
        val expensesForFriend = expensesService.getExpensesForFriend(friend)
        return HttpResponse.ok(convertExpensesToApplication(expensesForFriend))
    }

    @Get("/party/{partyId}")
    open fun getExpensesForParty(@QueryValue @NotNull partyId: UUID) : HttpResponse<List<ExpenseResponseWsDTO>> {
        val party = partiesService.getPartyOrThrowException(partyId)
        val expensesForParty = expensesService.getExpensesForParty(party)
        return HttpResponse.ok(convertExpensesToApplication(expensesForParty))
    }

    @Error(exception = ExpenseException::class)
    fun handleExpenseException(exception: ExpenseException): HttpResponse<ErrorWsDTO> {
        return HttpResponse.ok(ErrorWsDTO(false, exception.message!!))
    }

    private fun convertExpensesToApplication(expenses: List<Expense>?): List<ExpenseResponseWsDTO> {
        return if (expenses.isNullOrEmpty()) {
          emptyList()
        } else {
            expenses.map { expense -> expensesConverter.convertToApplication(expense) }
        }
    }
}
