package com.danisanga.shared.expenses.expenses.domain.services.impl

import com.danisanga.shared.expenses.expenses.domain.entities.Friend
import com.danisanga.shared.expenses.expenses.domain.entities.Party
import com.danisanga.shared.expenses.expenses.domain.exceptions.FriendException
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
    @Throws(FriendException::class)
    override fun createFriend(friend: Friend): Friend {
        return friendsRepository.save(friend)
                ?: throw FriendException("Something was wrong creating this Friend!")
    }

    override fun getFriend(id: UUID): Friend? {
        return friendsRepository.findById(id).getOrNull()
    }

    @Throws(FriendNotFoundException::class)
    override fun getFriendOrThrowException(id: UUID): Friend {
        return getFriend(id) ?: throw FriendNotFoundException("This is not your Friend!")
    }

    override fun getFriendsForParty(party: Party): List<UUID> {
        return friendsRepository.getFriendsForParty(party)
                .map { friend -> friend.id!! }
                .toList()
    }
}
