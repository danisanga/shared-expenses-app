package com.danisanga.shared.expenses.expenses.domain.converters

import com.danisanga.shared.expenses.expenses.application.dtos.CreateExpenseWsDTO
import com.danisanga.shared.expenses.expenses.application.dtos.ExpenseResponseWsDTO
import com.danisanga.shared.expenses.expenses.domain.entities.Expense
import com.danisanga.shared.expenses.expenses.domain.services.FriendsService
import com.danisanga.shared.expenses.expenses.domain.services.PartiesService
import jakarta.inject.Singleton
import java.time.LocalDate

@Singleton
class ExpensesConverter(
        private val partiesService: PartiesService,
        private val friendService: FriendsService
) {

    fun convertToDomain(createExpenseWsDTO: CreateExpenseWsDTO): Expense {
        return Expense(
                null,
                createExpenseWsDTO.quantity,
                createExpenseWsDTO.description,
                LocalDate.now(),
                partiesService.getPartyOrThrowException(createExpenseWsDTO.party),
                friendService.getFriendOrThrowException(createExpenseWsDTO.friend)
        )
    }

    fun convertToApplication(expense: Expense): ExpenseResponseWsDTO {
        return ExpenseResponseWsDTO(
                expense.id,
                expense.quantity,
                expense.friend
        )
    }
}
