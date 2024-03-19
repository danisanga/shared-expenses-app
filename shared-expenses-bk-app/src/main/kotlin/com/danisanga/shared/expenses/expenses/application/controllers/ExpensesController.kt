package com.danisanga.shared.expenses.expenses.application.controllers

import com.danisanga.shared.expenses.expenses.application.dtos.CreateExpenseWsDTO
import com.danisanga.shared.expenses.expenses.application.dtos.toDomain
import com.danisanga.shared.expenses.expenses.domain.entities.Expense
import com.danisanga.shared.expenses.expenses.domain.repositories.ExpensesRepository
import com.danisanga.shared.expenses.parties.domain.entities.Party
import com.danisanga.shared.expenses.parties.domain.repositories.PartiesRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import jakarta.validation.constraints.NotBlank
import java.time.LocalDate
import kotlin.jvm.optionals.getOrNull

@ExecuteOn(TaskExecutors.BLOCKING)
@Controller("/expenses")
open class ExpensesController(
        private val expensesRepository: ExpensesRepository,
        private val partiesRepository: PartiesRepository
) {

    @Post("/create")
    open fun createExpense(@Body("expense") expense: CreateExpenseWsDTO) : HttpResponse<CreateExpenseWsDTO> {
        val expenseDomain = expense.toDomain()
        expenseDomain.party = partiesRepository.findById(expense.party).getOrNull();
        expensesRepository.save(expenseDomain)
        return HttpResponse
                .created(expense)
    }
}