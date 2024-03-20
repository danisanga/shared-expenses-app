package com.danisanga.shared.expenses.expenses.application.controllers

import com.danisanga.shared.expenses.expenses.application.dtos.AddFriendWsDTO
import com.danisanga.shared.expenses.expenses.application.dtos.toDomain
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn

@ExecuteOn(TaskExecutors.BLOCKING)
@Controller("/expenses")
open class FriendsController() {

    @Post("/add-to-party")
    open fun createExpense(@Body("friend") request: AddFriendWsDTO) : HttpResponse<AddFriendWsDTO> {
        val requestToDomain = request.toDomain()
        // TODO - Completar
        return HttpResponse
                .created(request)
    }
}