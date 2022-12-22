package com.tadiuzzz.binchecker.ui.binDetails.model

import com.tadiuzzz.binchecker.domain.model.BinInfo

sealed class BinDetailsState {
    object Editing : BinDetailsState()
    data class Success(val binInfo: BinInfo) : BinDetailsState()
    data class Error(val errorText: String) : BinDetailsState()
    object Loading : BinDetailsState()
}