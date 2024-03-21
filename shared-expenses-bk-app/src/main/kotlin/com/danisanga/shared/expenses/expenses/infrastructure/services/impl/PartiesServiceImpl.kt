package com.danisanga.shared.expenses.expenses.infrastructure.services.impl

import com.danisanga.shared.expenses.expenses.domain.entities.Party
import com.danisanga.shared.expenses.expenses.domain.exceptions.PartyNotFoundException
import com.danisanga.shared.expenses.expenses.domain.repositories.PartiesRepository
import com.danisanga.shared.expenses.expenses.domain.services.PartiesService
import jakarta.inject.Singleton
import java.time.LocalDate
import java.util.*
import kotlin.jvm.optionals.getOrNull

@Singleton
class PartiesServiceImpl (
        private val partiesRepository: PartiesRepository
): PartiesService {
    override fun createParty(party: Party): Party? {
//        return partiesRepository.save(name, LocalDate.now())
        return partiesRepository.save(party)
    }

    override fun getParty(id: UUID): Party? {
        return partiesRepository.findById(id).getOrNull()
    }

    @Throws(PartyNotFoundException::class)
    override fun getPartyOrThrowException(id: UUID): Party {
        return getParty(id) ?: throw PartyNotFoundException("This Party does not exists!")
    }
}