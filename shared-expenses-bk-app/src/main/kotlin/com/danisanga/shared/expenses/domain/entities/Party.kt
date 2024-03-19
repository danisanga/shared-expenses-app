package com.danisanga.shared.expenses.domain.entities

import io.micronaut.data.annotation.DateCreated
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.data.annotation.MappedProperty
import io.micronaut.serde.annotation.Serdeable
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
        var createdTime: LocalDate
)