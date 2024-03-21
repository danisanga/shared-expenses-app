package com.danisanga.shared.expenses.expenses.domain.entities

import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate
import java.util.*

@Serdeable
@Entity
@Table(name = "friends")
data class Friend(
        @Id
        @GeneratedValue
        var id: UUID = UUID.randomUUID(),
        var name: String,
        var email: String,
        var creationTime: LocalDate,
)