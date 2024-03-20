package com.danisanga.shared.expenses.expenses.application.dtos

import com.danisanga.shared.expenses.expenses.domain.entities.Friend
import io.micronaut.serde.annotation.Serdeable
import java.time.LocalDate

@Serdeable
data class AddFriendWsDTO(
    var name: String,
    var email: String,
    var creationTime: LocalDate
)

fun AddFriendWsDTO.toDomain() = Friend(
        name = name,
        email = email,
        creationTime = creationTime
)
