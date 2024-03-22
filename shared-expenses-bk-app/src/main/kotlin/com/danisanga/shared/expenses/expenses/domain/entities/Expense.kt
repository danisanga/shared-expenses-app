package com.danisanga.shared.expenses.expenses.domain.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.*
import java.time.LocalDate
import java.util.*

@Serdeable
@Entity
@Table(name = "expenses")
data class Expense(
        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        var id: UUID?,
        var quantity: Double,
        var description: String,
        var creationTime: LocalDate,
        @JsonIgnore
        @ManyToOne
        var party: Party?,
        @ManyToOne
        var friend: Friend?
)