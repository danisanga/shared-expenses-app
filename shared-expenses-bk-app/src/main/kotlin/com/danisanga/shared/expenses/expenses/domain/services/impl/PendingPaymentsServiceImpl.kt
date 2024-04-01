package com.danisanga.shared.expenses.expenses.domain.services.impl

import com.danisanga.shared.expenses.expenses.domain.model.FriendBalance
import com.danisanga.shared.expenses.expenses.domain.model.PendingPayment
import com.danisanga.shared.expenses.expenses.domain.services.BalancesService
import com.danisanga.shared.expenses.expenses.domain.services.PendingPaymentsService
import jakarta.inject.Singleton
import java.util.*
import kotlin.math.abs

@Singleton
class PendingPaymentsServiceImpl(
        private val balancesService: BalancesService
): PendingPaymentsService {

    override fun getPendingPayments(party: UUID): List<PendingPayment> {
        val totalBalance = balancesService.getTotalBalance(party)!!
        if (Objects.isNull(totalBalance)) {
            return emptyList()
        }

        val recipients = totalBalance.friendBalances.filter { balance -> balance.quantity > 0 }.toMutableList()
        val debtors = totalBalance.friendBalances.filter { balance -> balance.quantity < 0 }.toMutableList()
        if (Objects.isNull(debtors)) {
            return emptyList()
        }

        val pendingPaymentsAsResult: MutableList<PendingPayment> = arrayListOf()
        preparePendingPayments(recipients, debtors, pendingPaymentsAsResult)
        return pendingPaymentsAsResult
    }

    private fun preparePendingPayments(
            recipients: MutableList<FriendBalance>,
            debtors: MutableList<FriendBalance>,
            pendingPayments: MutableList<PendingPayment>
    ): List<PendingPayment> {

        for (recipient in recipients) {
            val recipientQuantityToBePaid = recipient.quantity
            getDebtorPayment(debtors, recipients, recipient, recipientQuantityToBePaid, pendingPayments)
        }
        return pendingPayments
    }

    // TODO - Pending refactor.
    private fun getDebtorPayment(debtors: MutableList<FriendBalance>,
                                 recipients: MutableList<FriendBalance>,
                                 recipient: FriendBalance,
                                 recipientQuantityToBePaid: Double,
                                 pendingPayments: MutableList<PendingPayment>) {
        for (debtor in debtors) {
            val debtorMoneyToPay = abs(debtor.quantity)
            var totalPaid: Double
            var debtorRemainingMoney: Double
            // Has Debtor use all his money to pay?
            if (recipientQuantityToBePaid >= debtorMoneyToPay) {
                totalPaid = debtorMoneyToPay
                debtorRemainingMoney = 0.0
            } else {
                totalPaid = abs(debtorMoneyToPay - recipientQuantityToBePaid)
                if (totalPaid >= recipientQuantityToBePaid) {
                    totalPaid = recipientQuantityToBePaid
                }
                debtorRemainingMoney = debtorMoneyToPay - totalPaid
            }

            savePaymentRegistry(pendingPayments, recipient, debtor, totalPaid)

            if (debtorRemainingMoney <= 0.0) {
                debtors.remove(debtor)
            }

            // Check if recipient's debt has been paid.
            if (totalPaid == recipientQuantityToBePaid) {
                break;
            } else if (totalPaid < recipientQuantityToBePaid) {
                val remainingTotalPaid = recipientQuantityToBePaid - totalPaid
                recipient.quantity = remainingTotalPaid
                getDebtorPayment(debtors, recipients, recipient, remainingTotalPaid, pendingPayments)
            }
        }
    }

    private fun savePaymentRegistry(
            pendingPayments: MutableList<PendingPayment>,
            recipient: FriendBalance,
            debtor: FriendBalance,
            totalPaid: Double
    ) {
        var payment = pendingPayments
                .find { payment ->
                    payment.to.id!! == recipient.friend.id
                            && payment.from.id!! == debtor.friend.id
                }
        if (payment != null) {
            payment.quantity += totalPaid
        } else {
            payment = PendingPayment(
                    debtor.friend,
                    recipient.friend,
                    totalPaid
            )
            pendingPayments.add(payment)
        }
    }

}
