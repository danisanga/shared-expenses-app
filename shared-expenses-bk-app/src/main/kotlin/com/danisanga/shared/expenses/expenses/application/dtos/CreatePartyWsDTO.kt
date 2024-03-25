package com.danisanga.shared.expenses.expenses.application.dtos

import io.micronaut.serde.annotation.Serdeable
import jakarta.validation.constraints.NotBlank

@Serdeable
data class CreatePartyWsDTO(
        @NotBlank
        var name: String
)
