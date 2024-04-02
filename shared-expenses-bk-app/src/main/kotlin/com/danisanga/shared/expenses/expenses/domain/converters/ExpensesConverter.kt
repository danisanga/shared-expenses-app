package com.danisanga.shared.expenses.expenses.domain.converters

import com.danisanga.shared.expenses.expenses.application.dtos.CreateExpenseWsDTO
import com.danisanga.shared.expenses.expenses.application.dtos.ExpenseResponseWsDTO
import com.danisanga.shared.expenses.expenses.domain.model.entities.Expense
import com.danisanga.shared.expenses.expenses.domain.services.FriendsService
import com.danisanga.shared.expenses.expenses.domain.services.PartiesService
import jakarta.inject.Singleton

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

    fun convertAllToApplication(expenses: List<Expense>?): List<ExpenseResponseWsDTO> {
        return if (expenses.isNullOrEmpty()) {
            emptyList()
        } else {
            expenses.map { expense -> convertToApplication(expense) }
        }
    }
}
