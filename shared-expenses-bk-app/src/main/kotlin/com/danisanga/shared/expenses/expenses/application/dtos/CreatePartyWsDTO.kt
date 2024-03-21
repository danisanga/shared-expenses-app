package com.danisanga.shared.expenses.expenses.application.dtos

import com.danisanga.shared.expenses.expenses.domain.entities.Party
import io.micronaut.serde.annotation.Serdeable
import java.time.LocalDate

@Serdeable
data class CreatePartyWsDTO(
        var name: String
)

fun CreatePartyWsDTO.toDomain() = Party(
        id = null,
        name = name,
        creationTime = LocalDate.now()
)