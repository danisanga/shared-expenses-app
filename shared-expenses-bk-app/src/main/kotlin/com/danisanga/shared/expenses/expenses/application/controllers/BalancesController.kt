package com.danisanga.shared.expenses.expenses.application.controllers

import com.danisanga.shared.expenses.expenses.application.dtos.TotalBalanceResponseWsDTO
import com.danisanga.shared.expenses.expenses.domain.model.toApplication
import com.danisanga.shared.expenses.expenses.domain.services.BalancesService
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
        private val balancesService: BalancesService
) {
    @Get("/{partyId}")
    open fun getBalance(@QueryValue @NotNull partyId: UUID) : TotalBalanceResponseWsDTO? {
        val totalBalance = balancesService.getTotalBalance(partyId)
        return totalBalance?.toApplication()
    }
}