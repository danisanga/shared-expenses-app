package com.danisanga.shared.expenses.expenses.application.dtos

import com.danisanga.shared.expenses.expenses.domain.entities.Expense
import io.micronaut.serde.annotation.Serdeable
import java.time.LocalDate
import java.util.*

@Serdeable
data class CreateExpenseWsDTO(
    var quantity: Double,
    var description: String,
    var party: UUID
)

fun CreateExpenseWsDTO.toDomain() = Expense(
        quantity = quantity,
        description = description,
        party = null,
        creationTime = LocalDate.now()
)
