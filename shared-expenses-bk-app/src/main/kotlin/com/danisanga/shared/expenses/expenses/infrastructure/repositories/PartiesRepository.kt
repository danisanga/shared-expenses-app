package com.danisanga.shared.expenses.expenses.infrastructure.repositories

import com.danisanga.shared.expenses.expenses.domain.entities.Party
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import java.util.*

@Repository
interface PartiesRepository : CrudRepository<Party, UUID>