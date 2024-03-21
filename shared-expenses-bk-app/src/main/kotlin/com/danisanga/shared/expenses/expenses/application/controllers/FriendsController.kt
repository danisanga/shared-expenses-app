package com.danisanga.shared.expenses.expenses.application.controllers

import com.danisanga.shared.expenses.expenses.application.dtos.AddFriendWsDTO
import com.danisanga.shared.expenses.expenses.application.dtos.toDomain
import com.danisanga.shared.expenses.expenses.domain.services.PartiesService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn

@ExecuteOn(TaskExecutors.BLOCKING)
@Controller("/expenses")
open class FriendsController(
        private val partiesService: PartiesService
) {

    @Post("/add-to-party")
    open fun createAndAddFriendToParty(@Body("friend") request: AddFriendWsDTO) : HttpResponse<AddFriendWsDTO> {
        val partyUUID = request.party
        val party = partiesService.getPartyOrThrowException(partyUUID)
//        val requestToDomain = request.toDomain()
//        requestToDomain.party = party
        return HttpResponse
                .created(request)
    }
}