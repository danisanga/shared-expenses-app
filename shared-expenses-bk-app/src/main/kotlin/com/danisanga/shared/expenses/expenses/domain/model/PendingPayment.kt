package com.danisanga.shared.expenses.expenses.domain.model

import com.danisanga.shared.expenses.expenses.domain.entities.Friend
import io.micronaut.serde.annotation.Serdeable

@Serdeable
class PendingPayment (

    var from: Friend,
    var to: Friend,
    var quantity: Double

)