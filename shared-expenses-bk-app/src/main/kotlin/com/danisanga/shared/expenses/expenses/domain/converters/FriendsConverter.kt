package com.danisanga.shared.expenses.expenses.domain.converters

import com.danisanga.shared.expenses.expenses.application.dtos.AddFriendWsDTO
import com.danisanga.shared.expenses.expenses.application.dtos.FriendResponseWsDTO
import com.danisanga.shared.expenses.expenses.domain.entities.Friend
import com.danisanga.shared.expenses.expenses.domain.services.PartiesService
import jakarta.inject.Singleton

@Singleton
class FriendsConverter(
        private val partiesService: PartiesService
) {

    fun convertToDomain(addFriendWsDTO: AddFriendWsDTO): Friend {
        return Friend(
                null,
                addFriendWsDTO.name,
                addFriendWsDTO.email,
                null,
                partiesService.getPartyOrThrowException(addFriendWsDTO.party)
        )
    }

    fun convertToApplication(friend: Friend): FriendResponseWsDTO {
        return FriendResponseWsDTO(
                friend.id,
                friend.name,
                friend.email,
                friend.creationTime,
                friend.party
        )
    }
}
