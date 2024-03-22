package com.danisanga.shared.expenses.expenses.infrastructure.services.impl

import com.danisanga.shared.expenses.expenses.domain.entities.Friend
import com.danisanga.shared.expenses.expenses.domain.repositories.FriendsRepository
import com.danisanga.shared.expenses.expenses.domain.services.FriendsService
import jakarta.inject.Singleton

@Singleton
class FriendsServiceImpl (
        private val friendsRepository: FriendsRepository
): FriendsService {
    override fun createFriend(friend: Friend): Friend? {
        return friendsRepository.save(friend)
    }
}