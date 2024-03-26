package com.danisanga.shared.expenses.expenses.application.dtos

import com.danisanga.shared.expenses.expenses.domain.entities.Friend
import io.micronaut.serde.annotation.Serdeable

@Serdeable
class PendingPaymentResponseWsDTO (

    var from: Friend,
    var to: Friend,
    var quantity: Double

)