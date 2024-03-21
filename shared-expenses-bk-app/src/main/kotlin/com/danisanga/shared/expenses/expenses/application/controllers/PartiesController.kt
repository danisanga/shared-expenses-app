package com.danisanga.shared.expenses.expenses.application.controllers

import com.danisanga.shared.expenses.expenses.application.dtos.CreatePartyWsDTO
import com.danisanga.shared.expenses.expenses.application.dtos.PartyResponseWsDTO
import com.danisanga.shared.expenses.expenses.application.dtos.toDomain
import com.danisanga.shared.expenses.expenses.domain.entities.Party
import com.danisanga.shared.expenses.expenses.domain.entities.toApplication
import com.danisanga.shared.expenses.expenses.domain.services.PartiesService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.util.*

@ExecuteOn(TaskExecutors.BLOCKING)
@Controller("/parties")
open class PartiesController (
        private val partiesService: PartiesService
) {
    @Post("/create")
    open fun createParty(@Body @NotNull request: CreatePartyWsDTO) : HttpResponse<Party> {
        val party = partiesService.createParty(request.toDomain())
        return HttpResponse
                .created(party)
    }

    @Get("/{id}")
    open fun getParty(@QueryValue @NotNull id: UUID) : PartyResponseWsDTO? {
        val party = partiesService.getPartyOrThrowException(id)
        return party?.toApplication()
    }

    @Get("/{id}/balance/{friendId}")
    open fun getBalanceForFriend(@QueryValue @NotBlank id: UUID, @QueryValue @NotBlank friendId: UUID) : Party? {
        return null
    }
}