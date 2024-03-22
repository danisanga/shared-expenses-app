package com.danisanga.shared.expenses.expenses.application.dtos

import com.danisanga.shared.expenses.expenses.domain.entities.Friend
import io.micronaut.serde.annotation.Serdeable
import java.time.LocalDate
import java.util.*

@Serdeable
data class AddFriendWsDTO(
    var name: String,
    var email: String,
    var party: UUID
)

fun AddFriendWsDTO.toDomain() = Friend(
        id = null,
        name = name,
        email = email,
        party = null,
        creationTime = LocalDate.now()
)
