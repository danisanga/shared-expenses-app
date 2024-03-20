package com.danisanga.shared.expenses.expenses.infrastructure.services.impl

import com.danisanga.shared.expenses.expenses.domain.entities.Party
import com.danisanga.shared.expenses.expenses.domain.services.PartiesService
import jakarta.inject.Singleton
import java.time.LocalDate
import java.util.*
import kotlin.jvm.optionals.getOrNull

@Singleton
class PartiesServiceImpl (
        private val partiesService: com.danisanga.shared.expenses.expenses.domain.repositories.PartiesRepository
): PartiesService {
    override fun createParty(name: String): Party? {
        return partiesService.save(name, LocalDate.now())
    }

    override fun getParty(id: UUID): Party? {
        return partiesService.findById(id).getOrNull()
    }

    @Throws(RuntimeException::class)
    override fun getPartyOrThrowException(id: UUID): Party {
        return getParty(id) ?: throw RuntimeException("This Party does not exists!")
    }
}