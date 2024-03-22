package com.danisanga.shared.expenses.expenses.domain.repositories

import com.danisanga.shared.expenses.expenses.domain.entities.Friend
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import java.util.*

@Repository
interface FriendsRepository : CrudRepository<Friend, UUID>