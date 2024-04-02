package com.danisanga.shared.expenses.expenses.domain.converters

import com.danisanga.shared.expenses.expenses.application.dtos.AddFriendWsDTO
import com.danisanga.shared.expenses.expenses.application.dtos.FriendResponseWsDTO
import com.danisanga.shared.expenses.expenses.domain.model.entities.Friend
import com.danisanga.shared.expenses.expenses.domain.services.PartiesService
import jakarta.inject.Singleton
import java.time.LocalDate

@Singleton
class FriendsConverter(
        private val partiesService: PartiesService
) {

    fun convertToDomain(addFriendWsDTO: AddFriendWsDTO): Friend {
        return Friend(
                null,
                addFriendWsDTO.name,
                addFriendWsDTO.email,
                LocalDate.now(),
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
