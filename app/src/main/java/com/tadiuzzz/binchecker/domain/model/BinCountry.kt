package com.tadiuzzz.binchecker.domain.model

data class BinCountry(
    val numeric: String,
    val alpha2: String,
    val name: String,
    val emoji: String,
    val currency: String,
    val lattitude: Float,
    val longtitude: Float
)