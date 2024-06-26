package com.danisanga.shared.expenses.expenses.application.dtos

import io.micronaut.serde.annotation.Serdeable
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.util.*

@Serdeable
data class AddFriendWsDTO(
    @NotBlank
    var name: String,
    @NotBlank
    var email: String,
    @NotNull
    var party: UUID
)
