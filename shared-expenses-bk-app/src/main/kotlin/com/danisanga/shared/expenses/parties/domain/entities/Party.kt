package com.danisanga.shared.expenses.parties.domain.entities

import com.danisanga.shared.expenses.expenses.domain.entities.Expense
import io.micronaut.data.annotation.*
import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.CascadeType
import jakarta.persistence.FetchType
import jakarta.persistence.OneToMany
import java.time.LocalDate
import java.util.*

@Serdeable
@MappedEntity(value = "parties")
data class Party(
        @field:Id
        @field:GeneratedValue(GeneratedValue.Type.UUID)
        var id: UUID = UUID.randomUUID(),
        var name: String,
        @DateCreated
        var createdTime: LocalDate,
        @OneToMany(mappedBy = "party", cascade = [CascadeType.ALL], orphanRemoval = true)
        var expenses: Set<Expense> = HashSet()
)