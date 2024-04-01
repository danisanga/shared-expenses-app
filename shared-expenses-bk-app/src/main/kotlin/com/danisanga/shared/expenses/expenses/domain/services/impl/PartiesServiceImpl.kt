package com.danisanga.shared.expenses.expenses.domain.services.impl

import com.danisanga.shared.expenses.expenses.domain.entities.Party
import com.danisanga.shared.expenses.expenses.domain.exceptions.PartyException
import com.danisanga.shared.expenses.expenses.domain.exceptions.PartyNotFoundException
import com.danisanga.shared.expenses.expenses.infrastructure.repositories.PartiesRepository
import com.danisanga.shared.expenses.expenses.domain.services.PartiesService
import jakarta.inject.Singleton
import java.util.*
import kotlin.jvm.optionals.getOrNull

@Singleton
class PartiesServiceImpl (
        private val partiesRepository: PartiesRepository
): PartiesService {
    @Throws(PartyException::class)

    override fun createParty(party: Party): Party {
        return partiesRepository.save(party)
                ?: throw PartyException("Something was wrong creating this Party!")
    }

    override fun getParty(id: UUID): Party? {
        return partiesRepository.findById(id).getOrNull()
    }

    @Throws(PartyNotFoundException::class)
    override fun getPartyOrThrowException(id: UUID): Party {
        return getParty(id)
                ?: throw PartyNotFoundException("This Party does not exists!")
    }
}
