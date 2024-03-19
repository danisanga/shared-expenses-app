package com.danisanga.shared.expenses.expenses.application.dtos

import com.danisanga.shared.expenses.expenses.domain.entities.Expense
import com.danisanga.shared.expenses.parties.domain.entities.Party
import io.micronaut.serde.annotation.Serdeable
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

@Serdeable
data class CreateExpenseWsDTO(
    var quantity: Double,
    var description: String,
//    var friend: UUID,
    var party: UUID
)

fun CreateExpenseWsDTO.toDomain() = Expense(
        quantity = quantity,
        description = description,
//        party = party,
        party = null,
        createdTime = LocalDate.now()
)
