package com.danisanga.shared.expenses.expenses.application.controllers

import com.danisanga.shared.expenses.expenses.application.dtos.CreatePartyWsDTO
import com.danisanga.shared.expenses.expenses.application.dtos.ErrorWsDTO
import com.danisanga.shared.expenses.expenses.application.dtos.PartyResponseWsDTO
import com.danisanga.shared.expenses.expenses.domain.converters.PartiesConverter
import com.danisanga.shared.expenses.expenses.domain.exceptions.PartyNotFoundException
import com.danisanga.shared.expenses.expenses.domain.services.PartiesService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import java.util.*

@ExecuteOn(TaskExecutors.BLOCKING)
@Controller("/parties")
open class PartiesController (
        private val partiesService: PartiesService,
        private val partiesConverter: PartiesConverter
) {
    @Post("/create")
    open fun createParty(@Body @Valid createPartyWsDTO: CreatePartyWsDTO) : HttpResponse<PartyResponseWsDTO> {
        val party = partiesService.createParty(partiesConverter.convertToDomain(createPartyWsDTO))
        return HttpResponse
                .created(partiesConverter.convertToApplication(party))
    }

    @Get("/{id}")
    open fun getParty(@QueryValue @NotNull id: UUID) : HttpResponse<PartyResponseWsDTO> {
        val party = partiesService.getPartyOrThrowException(id)
        return HttpResponse
                .ok(partiesConverter.convertToApplication(party!!))
    }

    @Error(exception = PartyNotFoundException::class)
    fun handlePartyNotFoundException(exception: PartyNotFoundException): HttpResponse<ErrorWsDTO> {
        return HttpResponse.ok(ErrorWsDTO(false, exception.message!!))
    }
}