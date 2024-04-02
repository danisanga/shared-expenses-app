package com.danisanga.shared.expenses.expenses.domain.converters

import com.danisanga.shared.expenses.expenses.application.dtos.PendingPaymentResponseWsDTO
import com.danisanga.shared.expenses.expenses.domain.model.PendingPayment
import jakarta.inject.Singleton

@Singleton
class PendingPaymentsConverter {
    fun convertToApplication(pendingPayment: PendingPayment): PendingPaymentResponseWsDTO {
        return PendingPaymentResponseWsDTO(
            pendingPayment.from,
            pendingPayment.to,
            pendingPayment.quantity
        )
    }

    fun convertAllToApplication(pendingPayments: List<PendingPayment>?): List<PendingPaymentResponseWsDTO> {
        return if (pendingPayments.isNullOrEmpty()) {
            emptyList()
        } else {
            pendingPayments.map { pendingPayment -> convertToApplication(pendingPayment) }
        }
    }
}
