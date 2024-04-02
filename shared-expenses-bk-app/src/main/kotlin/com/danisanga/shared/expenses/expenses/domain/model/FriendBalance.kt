package com.danisanga.shared.expenses.expenses.domain.model

import com.danisanga.shared.expenses.expenses.domain.model.entities.Friend
import io.micronaut.serde.annotation.Serdeable
import jakarta.validation.constraints.NotNull

@Serdeable
data class FriendBalance(
    @NotNull
    var friend: Friend,
    @NotNull
    var quantity: Double
)
