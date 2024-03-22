package com.danisanga.shared.expenses.expenses.domain.services

import com.danisanga.shared.expenses.expenses.domain.entities.Friend

interface FriendsService {
    fun createFriend(friend: Friend): Friend?
}