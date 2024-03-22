package com.danisanga.shared.expenses.expenses.domain.entities

import com.danisanga.shared.expenses.expenses.application.dtos.FriendResponseWsDTO
import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.*
import java.time.LocalDate
import java.util.*

@Serdeable
@Entity
@Table(name = "friends")
data class Friend(
        @Id
        @GeneratedValue
        var id: UUID?,
        var name: String,
        var email: String,
        var creationTime: LocalDate,
        @ManyToOne
        var party: Party?,
        @OneToMany(mappedBy = "friend", fetch = FetchType.EAGER)
        var expenses: Set<Expense> = HashSet(),
)

fun Friend.toApplication() = FriendResponseWsDTO(
        id = id,
        name = name,
        email = email,
        creationTime = creationTime,
        party = party,
        expenses = expenses
)