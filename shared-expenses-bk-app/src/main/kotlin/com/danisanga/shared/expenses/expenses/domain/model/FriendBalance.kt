package com.danisanga.shared.expenses.expenses.domain.model

import com.danisanga.shared.expenses.expenses.domain.entities.Friend
import io.micronaut.serde.annotation.Serdeable

@Serdeable
class FriendBalance(

    var friend: Friend,
    var quantity: Double
)
