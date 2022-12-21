package com.tadiuzzz.binchecker.di

import com.tadiuzzz.binchecker.ui.SearchBinViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val viewModelKoinModule = module {

    singleOf(::SearchBinViewModel)

}