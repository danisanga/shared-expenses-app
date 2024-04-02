package com.danisanga.shared.expenses.expenses.application.controllers

import com.danisanga.shared.expenses.expenses.application.dtos.PendingPaymentResponseWsDTO
import com.danisanga.shared.expenses.expenses.domain.converters.PendingPaymentsConverter
import com.danisanga.shared.expenses.expenses.domain.services.PendingPaymentsService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import jakarta.validation.constraints.NotNull
import java.util.*

@ExecuteOn(TaskExecutors.BLOCKING)
@Controller("/payments")
open class PendingPaymentsController(
    private val pendingPaymentsService: PendingPaymentsService,
    private val pendingPaymentsConverter: PendingPaymentsConverter
) {

    @Get("/{partyId}")
    open fun getPayments(@QueryValue @NotNull partyId: UUID): HttpResponse<List<PendingPaymentResponseWsDTO>> {
        val pendingPayments = pendingPaymentsService.getPendingPayments(partyId)
        return HttpResponse
            .ok(pendingPaymentsConverter.convertAllToApplication(pendingPayments))
    }
}