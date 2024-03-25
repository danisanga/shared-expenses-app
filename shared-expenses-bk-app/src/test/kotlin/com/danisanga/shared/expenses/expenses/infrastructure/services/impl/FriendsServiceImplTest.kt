package com.danisanga.shared.expenses.expenses.infrastructure.services.impl

import com.danisanga.shared.expenses.expenses.domain.entities.Friend
import com.danisanga.shared.expenses.expenses.domain.entities.Party
import com.danisanga.shared.expenses.expenses.domain.exceptions.FriendNotFoundException
import com.danisanga.shared.expenses.expenses.domain.repositories.FriendsRepository
import com.danisanga.shared.expenses.expenses.domain.services.impl.FriendsServiceImpl
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate
import java.util.*

@MicronautTest
class FriendsServiceImplTest {

    val FRIEND_UUID = UUID.randomUUID()
    val PARTY_UUID = UUID.randomUUID()

    private val friendsRepository: FriendsRepository = mockk<FriendsRepository>()

    private val testObj = FriendsServiceImpl(friendsRepository)

    @Test
    fun `should return expected friend for specific UUID`() {
        val expectedFriend = Friend(
                FRIEND_UUID,
                "friend_name",
                "friend_email",
                LocalDate.now(),
                null
        )
        every {
            friendsRepository.findById(FRIEND_UUID)
        } returns Optional.of(expectedFriend)

        val result = testObj.getFriendOrThrowException(FRIEND_UUID)

        assert(expectedFriend == result)
        verify(exactly = 1) { friendsRepository.findById(FRIEND_UUID) }
    }

    @Test
    fun `should throw exception when friend does not exists for specific UUID`() {
        every {
            friendsRepository.findById(FRIEND_UUID)
        } returns Optional.empty()

        assertThrows<FriendNotFoundException> { testObj.getFriendOrThrowException(FRIEND_UUID) }
    }

    @Test
    fun `should create a new friend`() {
        val friendStub = Friend(
                FRIEND_UUID,
                "friend_name",
                "friend_email",
                LocalDate.now(),
                null
        )
        val expectedFriend = Friend(
                FRIEND_UUID,
                "friend_name",
                "friend_email",
                LocalDate.now(),
                null
        )

        every {
            friendsRepository.save(friendStub)
        } returns expectedFriend

        val result = testObj.createFriend(friendStub)

        assert(result.name == "friend_name")
        assert(result.email == "friend_email")
    }

    @Test
    fun `should return friends associated with a party`() {
        val partyStub = Party(
                PARTY_UUID,
                "party_name",
                LocalDate.now(),
                emptySet()
        )
        val friendStub = Friend(
                FRIEND_UUID,
                "friend_name",
                "friend_email",
                LocalDate.now(),
                null
        )
        every {
            friendsRepository.getFriendsForParty(partyStub)
        } returns listOf(friendStub)

        val result = testObj.getFriendsForParty(partyStub)

        assert(result.size == 1)
    }

}
