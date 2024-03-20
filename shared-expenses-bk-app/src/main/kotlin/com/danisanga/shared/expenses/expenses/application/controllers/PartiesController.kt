package com.danisanga.shared.expenses.expenses.application.controllers

import com.danisanga.shared.expenses.expenses.domain.entities.Party
import com.danisanga.shared.expenses.expenses.domain.services.PartiesService
import io.micronaut.core.annotation.NonNull
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.QueryValue
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import jakarta.validation.constraints.NotBlank
import java.time.LocalDate
import java.util.*

@ExecuteOn(TaskExecutors.BLOCKING)
@Controller("/parties")
open class PartiesController (
        private val partiesService: PartiesService
) {
    @Post("/create")
    open fun createParty(@Body("name") @NotBlank name: String) : HttpResponse<Party> {
        val party = partiesService.createParty(name)
        return HttpResponse
                .created(party)
    }

    @Get("/{id}")
    open fun getParty(@QueryValue @NotBlank id: UUID) : Party? {
        val party = partiesService.getPartyOrThrowException(id)
        return party
    }
}