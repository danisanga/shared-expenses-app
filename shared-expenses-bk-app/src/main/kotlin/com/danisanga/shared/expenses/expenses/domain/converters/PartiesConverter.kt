package com.danisanga.shared.expenses.expenses.domain.converters

import com.danisanga.shared.expenses.expenses.application.dtos.CreatePartyWsDTO
import com.danisanga.shared.expenses.expenses.application.dtos.PartyResponseWsDTO
import com.danisanga.shared.expenses.expenses.domain.entities.Party
import com.danisanga.shared.expenses.expenses.domain.services.PartiesService
import jakarta.inject.Singleton

@Singleton
class PartiesConverter(
        private val partiesService: PartiesService
) {

    fun convertToDomain(createPartyWsDTO: CreatePartyWsDTO): Party {
        return Party(
                null,
                createPartyWsDTO.name,
                null,
                emptySet()
        )
    }

    fun convertToApplication(party: Party): PartyResponseWsDTO {
        return PartyResponseWsDTO(
                party.id,
                party.name,
                party.creationTime,
                party.expenses
        )
    }
}
