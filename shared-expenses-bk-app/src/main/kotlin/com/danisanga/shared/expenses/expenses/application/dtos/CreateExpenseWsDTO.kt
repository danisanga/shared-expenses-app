package com.danisanga.shared.expenses.expenses.application.dtos

import io.micronaut.serde.annotation.Serdeable
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import java.util.*

@Serdeable
data class CreateExpenseWsDTO(
    @NotNull
    @Positive
    var quantity: Double,
    @NotBlank
    var description: String,
    @NotNull
    var party: UUID,
    @NotNull
    var friend: UUID
)
