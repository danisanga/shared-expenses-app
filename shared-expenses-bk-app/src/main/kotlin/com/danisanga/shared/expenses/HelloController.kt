package com.danisanga.shared.expenses

import com.danisanga.shared.expenses.domain.entities.Party
import com.danisanga.shared.expenses.domain.repositories.PartiesRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import jakarta.validation.constraints.NotBlank
import java.time.LocalDate
import java.util.UUID

@ExecuteOn(TaskExecutors.BLOCKING)
@Controller("/hello")
open class HelloController(private val partiesRepository: PartiesRepository) {

    @Get
    @Produces(MediaType.TEXT_PLAIN)
    fun index() = "Hello World"

    @Post
    open fun save(@Body("name") @NotBlank name: String) : HttpResponse<Party> {
        val party = partiesRepository.save(name, LocalDate.now())

        return HttpResponse
                .created(party)
    }
}