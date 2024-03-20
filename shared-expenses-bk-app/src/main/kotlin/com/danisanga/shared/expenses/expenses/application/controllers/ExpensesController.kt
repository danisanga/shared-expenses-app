package com.danisanga.shared.expenses.expenses.application.controllers

import com.danisanga.shared.expenses.expenses.application.dtos.CreateExpenseWsDTO
import com.danisanga.shared.expenses.expenses.application.dtos.toDomain
import com.danisanga.shared.expenses.expenses.domain.services.ExpensesService
import com.danisanga.shared.expenses.expenses.domain.services.PartiesService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn

@ExecuteOn(TaskExecutors.BLOCKING)
@Controller("/expenses")
open class ExpensesController(
        private val expensesService: ExpensesService,
        private val partiesService: PartiesService
) {

    @Post("/create")
    open fun createExpense(@Body("expense") expense: CreateExpenseWsDTO) : HttpResponse<CreateExpenseWsDTO> {
        val expenseDomain = expense.toDomain()
        expenseDomain.party = partiesService.getPartyOrThrowException(expense.party);
        expensesService.createExpense(expenseDomain)
        return HttpResponse
                .created(expense)
    }
}