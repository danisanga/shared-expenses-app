package com.danisanga.shared.expenses.expenses.infrastructure.services.impl

import com.danisanga.shared.expenses.expenses.domain.entities.Party
import com.danisanga.shared.expenses.expenses.domain.exceptions.PartyNotFoundException
import com.danisanga.shared.expenses.expenses.domain.repositories.PartiesRepository
import com.danisanga.shared.expenses.expenses.domain.services.impl.PartiesServiceImpl
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate
import java.util.*

@MicronautTest
class PartiesServiceImplTest {

    val PARTY_UUID = UUID.randomUUID()

    private val partiesRepository: PartiesRepository = mockk<PartiesRepository>()

    private val testObj = PartiesServiceImpl(partiesRepository)

    @Test
    fun `should return expected party for specific UUID`() {
        val expectedParty = Party(
                PARTY_UUID,
                "party_name",
                LocalDate.now(),
                emptySet(),
                emptySet()
        )
        every {
            partiesRepository.findById(PARTY_UUID)
        } returns Optional.of(expectedParty)

        val result = testObj.getPartyOrThrowException(PARTY_UUID)

        assert(expectedParty == result)
        verify(exactly = 1) { partiesRepository.findById(PARTY_UUID) }
    }

    @Test
    fun `should throw exception when party does not exists for specific UUID`() {
        every {
            partiesRepository.findById(PARTY_UUID)
        } returns Optional.empty()

        assertThrows<PartyNotFoundException> { testObj.getPartyOrThrowException(PARTY_UUID) }
    }

    @Test
    fun `should create a new party`() {
        val partyStub = Party(
                PARTY_UUID,
                "party_name",
                LocalDate.now(),
                emptySet(),
                emptySet()
        )
        val expectedParty = Party(
                PARTY_UUID,
                "party_name",
                LocalDate.now(),
                emptySet(),
                emptySet()
        )

        every {
            partiesRepository.save(partyStub)
        } returns expectedParty

        testObj.createParty(partyStub)

        assert(partyStub.name == "party_name")
    }


}
