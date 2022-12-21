package com.tadiuzzz.binchecker.domain.model

data class BinInfo(
    val number: BinNumber,
    val scheme: String,
    val type: String,
    val brand: String,
    val prepaid: Boolean,
    val country: BinCountry,
    val bank: BinBank
)