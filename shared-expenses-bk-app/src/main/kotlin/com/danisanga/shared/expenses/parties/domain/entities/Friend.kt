package com.danisanga.shared.expenses.parties.domain.entities

import io.micronaut.data.annotation.DateCreated
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.FetchType
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

@Serdeable
@MappedEntity(value = "friends")
data class Friend(
        @field:Id
        @field:GeneratedValue(GeneratedValue.Type.UUID)
        var id: UUID = UUID.randomUUID(),
        var name: String,
        @DateCreated
        var createdTime: LocalDate,
)