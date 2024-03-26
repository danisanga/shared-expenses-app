package com.danisanga.shared.expenses.expenses.domain.services

import com.danisanga.shared.expenses.expenses.domain.model.PendingPayment
import java.util.*

interface PendingPaymentsService {

    fun getPendingPayments(party: UUID): List<PendingPayment>
}
