package com.danisanga.shared.expenses.expenses.domain.services

import com.danisanga.shared.expenses.expenses.domain.model.entities.Friend
import com.danisanga.shared.expenses.expenses.domain.model.entities.Party
import java.util.*

interface FriendsService {
    fun createFriend(friend: Friend): Friend

    fun getFriend(id: UUID): Friend?

    fun getFriendOrThrowException(id : UUID): Friend?

    fun getFriendsForParty(party: Party): List<UUID>
}