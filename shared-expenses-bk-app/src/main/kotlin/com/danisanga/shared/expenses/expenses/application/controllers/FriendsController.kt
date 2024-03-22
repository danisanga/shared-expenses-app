package com.danisanga.shared.expenses.expenses.application.controllers

import com.danisanga.shared.expenses.expenses.application.dtos.AddFriendWsDTO
import com.danisanga.shared.expenses.expenses.application.dtos.FriendResponseWsDTO
import com.danisanga.shared.expenses.expenses.application.dtos.toDomain
import com.danisanga.shared.expenses.expenses.domain.entities.toApplication
import com.danisanga.shared.expenses.expenses.domain.services.FriendsService
import com.danisanga.shared.expenses.expenses.domain.services.PartiesService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import jakarta.validation.constraints.NotNull
import java.util.*

@ExecuteOn(TaskExecutors.BLOCKING)
@Controller("/friends")
open class FriendsController(
        private val friendsService: FriendsService,
        private val partiesService: PartiesService
) {

    @Post("/add-to-party")
    open fun createAndAddFriendToParty(@Body request: AddFriendWsDTO) : HttpResponse<FriendResponseWsDTO> {
        val friendToDomain = request.toDomain()
        friendToDomain.party = partiesService.getPartyOrThrowException(request.party)!!;
        val friend = friendsService.createFriend(friendToDomain)
        return HttpResponse
                .created(friend?.toApplication())
    }

    @Get("/{id}")
    open fun getFriend(@QueryValue @NotNull id: UUID) : FriendResponseWsDTO? {
        val friend = friendsService.getFriendOrThrowException(id)
        return friend?.toApplication()
    }
}