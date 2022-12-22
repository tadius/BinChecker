package com.tadiuzzz.binchecker.domain.model

data class BinInfo(
    val id: Long? = null,
    val number: BinNumber? = null,
    val scheme: String,
    val type: String,
    val brand: String,
    val prepaid: Boolean,
    val country: BinCountry?,
    val bank: BinBank?,
    val bin: String? = null,
)