package com.danisanga.shared.expenses.expenses.application.dtos

import com.danisanga.shared.expenses.expenses.domain.model.entities.Expense
import io.micronaut.serde.annotation.Serdeable
import java.time.LocalDate
import java.util.*

@Serdeable
data class PartyResponseWsDTO(
        var id: UUID?,
        var name: String,
        var creationTime: LocalDate?,
        var expenses: Set<Expense> = HashSet()
)