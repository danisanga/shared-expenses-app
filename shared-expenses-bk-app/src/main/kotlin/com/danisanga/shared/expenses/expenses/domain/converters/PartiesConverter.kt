package com.danisanga.shared.expenses.expenses.domain.converters

import com.danisanga.shared.expenses.expenses.application.dtos.CreatePartyWsDTO
import com.danisanga.shared.expenses.expenses.application.dtos.PartyResponseWsDTO
import com.danisanga.shared.expenses.expenses.domain.model.entities.Party
import jakarta.inject.Singleton
import java.time.LocalDate

@Singleton
class PartiesConverter {

    fun convertToDomain(createPartyWsDTO: CreatePartyWsDTO): Party {
        return Party(
                null,
                createPartyWsDTO.name,
                LocalDate.now(),
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
