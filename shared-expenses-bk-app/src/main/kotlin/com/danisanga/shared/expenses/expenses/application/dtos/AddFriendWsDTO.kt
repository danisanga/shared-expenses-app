package com.danisanga.shared.expenses.expenses.application.dtos

import com.danisanga.shared.expenses.expenses.domain.entities.Friend
import com.danisanga.shared.expenses.expenses.domain.entities.Party
import io.micronaut.serde.annotation.Serdeable
import java.time.LocalDate
import java.util.UUID

@Serdeable
data class AddFriendWsDTO(
    var name: String,
    var email: String,
    var party: UUID,
    var creationTime: LocalDate
)

fun AddFriendWsDTO.toDomain() = Friend(
        name = name,
        email = email,
//        party = null,
//        expense = null,
        creationTime = creationTime
)
