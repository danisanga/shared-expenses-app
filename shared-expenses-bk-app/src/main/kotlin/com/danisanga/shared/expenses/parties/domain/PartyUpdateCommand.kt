package com.danisanga.shared.expenses.parties.domain

import io.micronaut.serde.annotation.Serdeable
import jakarta.validation.constraints.NotBlank
import java.util.Date
import java.util.UUID

@Serdeable
data class PartyUpdateCommand(
        val id: UUID,
        @field:NotBlank val name: String,
        val createdTime: Date
)