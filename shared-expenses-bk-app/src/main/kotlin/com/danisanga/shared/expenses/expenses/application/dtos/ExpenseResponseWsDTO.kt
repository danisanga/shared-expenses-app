package com.danisanga.shared.expenses.expenses.application.dtos

import io.micronaut.serde.annotation.Serdeable
import java.util.*

@Serdeable
data class ExpenseResponseWsDTO(
        var id: UUID?,
        var quantity: Double
)