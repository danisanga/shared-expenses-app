package com.danisanga.shared.expenses.expenses.domain.converters

interface GenericConverter<I, O> {

    fun convertToDomain(toConvert: I): O
    fun convertToApplication(toConvert: O): I

}
