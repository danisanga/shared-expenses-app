package com.danisanga.shared.expenses.parties.application

import com.danisanga.shared.expenses.parties.domain.entities.Party
import com.danisanga.shared.expenses.parties.domain.repositories.PartiesRepository
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
import kotlin.jvm.optionals.getOrNull

@ExecuteOn(TaskExecutors.BLOCKING)
@Controller("/parties")
open class PartiesController(private val partiesRepository: PartiesRepository) {

    @Post("/create")
    open fun createParty(@Body("name") @NotBlank name: String) : HttpResponse<Party> {
        val party = partiesRepository.save(name, LocalDate.now())

        return HttpResponse
                .created(party)
    }

    @Get("/{id}")
    open fun getParty(@QueryValue @NotBlank id: UUID) : @NonNull Optional<Party>? {
        val party = partiesRepository.findById(id)
        var expenses = party.getOrNull()?.expenses
        return party
    }
}