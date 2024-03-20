package com.danisanga.shared.expenses.expenses.domain.services

import com.danisanga.shared.expenses.expenses.domain.entities.Party
import java.util.*

interface PartiesService {
    fun createParty(name: String): Party?

    fun getParty(id: UUID): Party?

    fun getPartyOrThrowException(id :UUID): Party?
}