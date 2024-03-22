package com.danisanga.shared.expenses.expenses.infrastructure.services.impl

import com.danisanga.shared.expenses.expenses.domain.entities.Friend
import com.danisanga.shared.expenses.expenses.domain.exceptions.FriendNotFoundException
import com.danisanga.shared.expenses.expenses.domain.repositories.FriendsRepository
import com.danisanga.shared.expenses.expenses.domain.services.FriendsService
import jakarta.inject.Singleton
import java.util.*
import kotlin.jvm.optionals.getOrNull

@Singleton
class FriendsServiceImpl (
        private val friendsRepository: FriendsRepository
): FriendsService {
    override fun createFriend(friend: Friend): Friend? {
        return friendsRepository.save(friend)
    }

    override fun getFriend(id: UUID): Friend? {
        return friendsRepository.findById(id).getOrNull()
    }

    @Throws(FriendNotFoundException::class)
    override fun getFriendOrThrowException(id: UUID): Friend {
        return getFriend(id) ?: throw FriendNotFoundException("This is not your Friend!")
    }
}