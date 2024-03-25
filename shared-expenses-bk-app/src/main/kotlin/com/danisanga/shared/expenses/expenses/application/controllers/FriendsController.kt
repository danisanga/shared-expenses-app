package com.danisanga.shared.expenses.expenses.application.controllers

import com.danisanga.shared.expenses.expenses.application.dtos.AddFriendWsDTO
import com.danisanga.shared.expenses.expenses.application.dtos.ErrorWsDTO
import com.danisanga.shared.expenses.expenses.application.dtos.FriendResponseWsDTO
import com.danisanga.shared.expenses.expenses.domain.converters.FriendsConverter
import com.danisanga.shared.expenses.expenses.domain.exceptions.FriendNotFoundException
import com.danisanga.shared.expenses.expenses.domain.services.FriendsService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import java.util.*

@ExecuteOn(TaskExecutors.BLOCKING)
@Controller("/friends")
open class FriendsController(
        private val friendsService: FriendsService,
        private val friendsConverter: FriendsConverter
) {

    @Post("/add-to-party")
    open fun createAndAddFriendToParty(@Body @Valid request: AddFriendWsDTO) : HttpResponse<FriendResponseWsDTO> {
        val friendToDomain = friendsConverter.convertToDomain(request)
        val friend = friendsService.createFriend(friendToDomain)
        return HttpResponse
                .created(friendsConverter.convertToApplication(friend))
    }

    @Get("/{id}")
    open fun getFriend(@QueryValue @NotNull id: UUID) : HttpResponse<FriendResponseWsDTO> {
        val friend = friendsService.getFriendOrThrowException(id)
        return HttpResponse
                .ok(friendsConverter.convertToApplication(friend!!))
    }

    @Error(exception = FriendNotFoundException::class)
    fun handlePartyNotFoundException(exception: FriendNotFoundException): HttpResponse<ErrorWsDTO> {
        return HttpResponse.ok(ErrorWsDTO(false, exception.message!!))
    }
}