package com.danisanga.shared.expenses.expenses.application.controllers

import com.danisanga.shared.expenses.expenses.application.dtos.TotalBalanceResponseWsDTO
import com.danisanga.shared.expenses.expenses.domain.converters.BalancesConverter
import com.danisanga.shared.expenses.expenses.domain.services.BalancesService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import jakarta.validation.constraints.NotNull
import java.util.*

@ExecuteOn(TaskExecutors.BLOCKING)
@Controller("/balances")
open class BalancesController (
        private val balancesService: BalancesService,
        private val balancesConverter: BalancesConverter
) {
    @Get("/{partyId}")
    open fun getBalance(@QueryValue @NotNull partyId: UUID) : HttpResponse<TotalBalanceResponseWsDTO> {
        val totalBalance = balancesService.getTotalBalance(partyId)
        return HttpResponse.ok(balancesConverter.convertToApplication(totalBalance!!))
    }
}