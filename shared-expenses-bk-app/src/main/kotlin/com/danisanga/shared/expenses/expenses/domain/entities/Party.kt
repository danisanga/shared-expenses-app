package com.danisanga.shared.expenses.expenses.domain.entities

import com.danisanga.shared.expenses.expenses.application.dtos.PartyResponseWsDTO
import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.*
import java.time.LocalDate
import java.util.*

@Entity
@Table(name = "parties")
class Party(
        @Id
        @GeneratedValue
        var id: UUID?,
        var name: String,
        @Column(name = "creation_time")
        var creationTime: LocalDate?,
        @OneToMany(mappedBy = "party", fetch = FetchType.EAGER)
        var expenses: Set<Expense> = HashSet(),
        @OneToMany(mappedBy = "party", fetch = FetchType.EAGER)
        var friends: Set<Friend> = HashSet(),
)

fun Party.toApplication() = PartyResponseWsDTO(
        id = id,
        name = name,
        creationTime = creationTime,
        expenses = expenses,
        friends = friends
)