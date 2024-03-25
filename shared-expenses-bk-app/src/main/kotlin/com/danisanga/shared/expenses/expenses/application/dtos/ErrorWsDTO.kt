package com.danisanga.shared.expenses.expenses.application.dtos

import io.micronaut.core.util.StringUtils
import io.micronaut.serde.annotation.Serdeable
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

@Serdeable
data class ErrorWsDTO(
        @NotNull
        var success: Boolean,
        @NotBlank
        var errorMessage: String
)