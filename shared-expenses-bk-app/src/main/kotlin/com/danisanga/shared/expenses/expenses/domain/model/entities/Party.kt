package com.danisanga.shared.expenses.expenses.domain.model.entities

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
        var expenses: Set<Expense> = HashSet()
)
