package com.danisanga.shared.expenses.expenses.infrastructure.repositories

import com.danisanga.shared.expenses.expenses.domain.entities.Expense
import com.danisanga.shared.expenses.expenses.domain.entities.Friend
import com.danisanga.shared.expenses.expenses.domain.entities.Party
import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import java.util.*

@Repository
interface FriendsRepository : CrudRepository<Friend, UUID> {
    @Query("FROM Friend b WHERE b.party = :party")
    fun getFriendsForParty(party: Party?): List<Friend>
}