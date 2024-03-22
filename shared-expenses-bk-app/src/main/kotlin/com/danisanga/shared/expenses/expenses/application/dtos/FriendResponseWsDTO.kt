package com.danisanga.shared.expenses.expenses.application.dtos

import com.danisanga.shared.expenses.expenses.domain.entities.Expense
import com.danisanga.shared.expenses.expenses.domain.entities.Party
import com.fasterxml.jackson.annotation.JsonIgnore
import io.micronaut.serde.annotation.Serdeable
import java.time.LocalDate
import java.util.*

@Serdeable
data class FriendResponseWsDTO(
        var id: UUID?,
        var name: String,
        var email: String,
        var creationTime: LocalDate?,
        @JsonIgnore
        var party: Party?,
        var expenses: Set<Expense> = HashSet(),
)