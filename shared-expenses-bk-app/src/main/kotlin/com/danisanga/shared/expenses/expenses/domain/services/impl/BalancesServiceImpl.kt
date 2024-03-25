package com.danisanga.shared.expenses.expenses.domain.services.impl

import com.danisanga.shared.expenses.expenses.domain.entities.Friend
import com.danisanga.shared.expenses.expenses.domain.model.FriendBalance
import com.danisanga.shared.expenses.expenses.domain.model.PendingPayment
import com.danisanga.shared.expenses.expenses.domain.model.TotalBalance
import com.danisanga.shared.expenses.expenses.domain.services.BalancesService
import com.danisanga.shared.expenses.expenses.domain.services.ExpensesService
import com.danisanga.shared.expenses.expenses.domain.services.FriendsService
import com.danisanga.shared.expenses.expenses.domain.services.PartiesService
import jakarta.inject.Singleton
import java.util.*
import kotlin.math.abs

@Singleton
class BalancesServiceImpl(
        private val expensesService: ExpensesService,
        private val partiesService: PartiesService,
        private val friendsService: FriendsService
): BalancesService {
    override fun getTotalBalance(partyId: UUID): TotalBalance {
        val party = partiesService.getPartyOrThrowException(partyId)
        val expensesForParty = expensesService.getExpensesForParty(party)
        val friendsForParty = friendsService.getFriendsForParty(party!!)
        val numberOfFriends = friendsForParty.size
        val totalSpent = expensesForParty.sumOf { it.quantity }

        val friendBalances = mutableListOf<FriendBalance>()
        for (uuid in friendsForParty) {
            val friend = friendsService.getFriendOrThrowException(uuid)
            val friendBalance = calculateFriendBalance(friend!!, totalSpent, numberOfFriends)
            friendBalances.add(friendBalance)
        }
        val totalBalance = TotalBalance(friendBalances)
        return totalBalance
    }

    private fun calculateFriendBalance(friend: Friend, totalSpent: Double, numberOfFriends: Int): FriendBalance {
        val expensesForFriend = expensesService.getExpensesForFriend(friend)

        val sumOf = expensesForFriend.sumOf { it.quantity }
        val sumOfBalance = getFriendBalance(sumOf, totalSpent, numberOfFriends)
        val friendBalance = FriendBalance(
                friend,
                Math.round(sumOfBalance * 100.0) / 100.0
        )
        return friendBalance
    }

    private fun getFriendBalance(balance: Double, totalSpent: Double, numberOfFriends: Int): Double {
        return balance - (totalSpent / numberOfFriends)
    }

    override fun getPayments(partyId: UUID): List<PendingPayment> {
        val balance = getTotalBalance(partyId)

        var hayQuePagar = balance.friendBalances.filter { balance -> balance.quantity > 0 }.toMutableList()
        var tienenQuePagar = balance.friendBalances.filter { balance -> balance.quantity < 0 }.toMutableList()

        var pendingPayments: MutableList<PendingPayment> = arrayListOf()
        preparePendingPayments(hayQuePagar, tienenQuePagar, pendingPayments)

        return pendingPayments
    }

    private fun preparePendingPayments(
            hayQuePagar: MutableList<FriendBalance>,
            tienenQuePagar: MutableList<FriendBalance>,
            pendingPayments: MutableList<PendingPayment>
    ): List<PendingPayment> {

        for (hayQue in hayQuePagar) {
            var totalAPagar = hayQue.quantity
            println(String.format("----- Calculando pagos para %s -----", hayQue.friend.name))
            getPayment(tienenQuePagar, totalAPagar, pendingPayments, hayQue, hayQuePagar)
        }

        return pendingPayments
    }

    private fun getPayment(tienenQuePagar: MutableList<FriendBalance>,
                           totalAPagar: Double,
                           pendingPayments: MutableList<PendingPayment>,
                           hayQue: FriendBalance,
                           hayQuePagar: MutableList<FriendBalance>) {
        // Miramos a los deudores
        for (tienenQue in tienenQuePagar) {
            var saldoParaPagar = abs(tienenQue.quantity)

            var totalPagado = 0.0
            var dineroParaPagarDeudasRestante = 0.0
            if (totalAPagar >= saldoParaPagar) {
                // El deudor se queda sin nada
                totalPagado = saldoParaPagar
                dineroParaPagarDeudasRestante = 0.0
            } else {
                // Calculamos lo que le queda al deudor
                totalPagado = abs(saldoParaPagar - totalAPagar)
                if (totalPagado >= totalAPagar) {
                    totalPagado = totalAPagar
                }
                dineroParaPagarDeudasRestante = saldoParaPagar - totalPagado
            }

            var payment = pendingPayments
                    .find { payment -> payment.to.id!! == hayQue.friend.id && payment.from.id!! == tienenQue.friend.id}
            if (payment != null) {
                payment.quantity += totalPagado
            } else {
                // Creamos registro de pagos
                payment = PendingPayment(
                        tienenQue.friend,
                        hayQue.friend,
                        totalPagado
                )
                pendingPayments.add(payment)
            }

            println(String.format("%s ha pagado %s a %s (de una deuda de %s)", tienenQue.friend.name, totalPagado, hayQue.friend.name, totalAPagar))

            // Comprobamos si le queda dinero al deudor...
            if (dineroParaPagarDeudasRestante <= 0.0) {
                tienenQuePagar.remove(tienenQue) // Si no le queda, lo eliminamos
            }

            // Comprobamos si se ha saldado la deuda...
            if (totalPagado == totalAPagar) {
//                hayQuePagar.remove(hayQue) // Si la ha pagado, eliminamos a uno de los que tienen que recibir dinero
                break;
            } else if (totalPagado < totalAPagar) {
                // Si no la ha saldado, repetimos...
                var dineroPagadoRestante = totalAPagar - totalPagado
                hayQue.quantity = dineroPagadoRestante
                getPayment(tienenQuePagar, dineroPagadoRestante, pendingPayments, hayQue, hayQuePagar)
            }
        }
    }
}
