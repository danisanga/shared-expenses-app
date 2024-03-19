package com.danisanga.shared.expenses.expenses.domain.entities

import com.danisanga.shared.expenses.parties.domain.entities.Party
import io.micronaut.data.annotation.*
import io.micronaut.data.annotation.sql.JoinColumn
import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToOne
import java.time.LocalDate
import java.util.*

@Serdeable
@MappedEntity(value = "expenses")
data class Expense(
        @field:Id
        @field:GeneratedValue(GeneratedValue.Type.UUID)
        var id: UUID = UUID.randomUUID(),
        var quantity: Double,
        var description: String,
        @DateCreated
        var createdTime: LocalDate,
//        @MappedProperty(value = "friend_id")
//        var friend: UUID,
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "party_id")
        var party: Party?
)