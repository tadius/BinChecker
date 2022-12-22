package com.tadiuzzz.binchecker.di

import com.tadiuzzz.binchecker.ui.binDetails.BinDetailsViewModel
import com.tadiuzzz.binchecker.ui.searchBin.BinHistoryViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val viewModelKoinModule = module {

    singleOf(::BinHistoryViewModel)
    singleOf(::BinDetailsViewModel)

}