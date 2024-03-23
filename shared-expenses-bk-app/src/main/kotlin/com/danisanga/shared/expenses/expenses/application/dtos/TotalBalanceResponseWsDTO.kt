package com.danisanga.shared.expenses.expenses.application.dtos

import com.danisanga.shared.expenses.expenses.domain.model.FriendBalance
import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class TotalBalanceResponseWsDTO(
        var friendBalances: List<FriendBalance>
)