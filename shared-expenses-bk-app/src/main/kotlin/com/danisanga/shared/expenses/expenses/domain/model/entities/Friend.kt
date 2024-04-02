package com.danisanga.shared.expenses.expenses.domain.model.entities

import com.fasterxml.jackson.annotation.JsonIgnore
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
        @Column(name = "name")
        var name: String,
        @Column(name = "email")
        var email: String,
        @Column(name = "creation_time")
        var creationTime: LocalDate?,
        @ManyToOne
        @JsonIgnore
        var party: Party?
)