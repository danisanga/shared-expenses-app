package com.danisanga.shared.expenses.expenses.domain.model

import com.danisanga.shared.expenses.expenses.domain.model.entities.Friend

class PendingPayment (

    var from: Friend,
    var to: Friend,
    var quantity: Double

)