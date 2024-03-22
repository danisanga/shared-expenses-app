package com.danisanga.shared.expenses.expenses.domain.entities

import com.danisanga.shared.expenses.expenses.application.dtos.FriendResponseWsDTO
import com.fasterxml.jackson.annotation.JsonIgnore
import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.*
import java.time.LocalDate
import java.util.*

@Serdeable
@Entity
@Table(name = "friends")
class Friend(
        @Id
        @GeneratedValue
        var id: UUID?,
        @Column(name = "name")
        var name: String,
        @Column(name = "email")
        var email: String,
        @Column(name = "creation_time")
        var creationTime: LocalDate?,
        @ManyToOne
        @JsonIgnore
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