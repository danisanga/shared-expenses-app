package com.danisanga.shared.expenses.expenses.domain.converters

import com.danisanga.shared.expenses.expenses.application.dtos.TotalBalanceResponseWsDTO
import com.danisanga.shared.expenses.expenses.domain.model.TotalBalance
import jakarta.inject.Singleton

@Singleton
class BalancesConverter {

    fun convertToApplication(totalBalance: TotalBalance): TotalBalanceResponseWsDTO {
        return TotalBalanceResponseWsDTO(
                totalBalance.friendBalances
        )
    }
}
