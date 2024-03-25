package com.danisanga.shared.expenses.expenses.domain.services

import com.danisanga.shared.expenses.expenses.domain.model.TotalBalance
import java.util.*

interface BalancesService {
    fun getTotalBalance(partyId: UUID): TotalBalance?

    fun getPayments(partyId: UUID): Any
}