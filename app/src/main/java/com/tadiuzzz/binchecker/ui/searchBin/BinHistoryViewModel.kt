package com.tadiuzzz.binchecker.ui.searchBin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class BinHistoryViewModel : ViewModel() {

    private val _openSearchBinScreenEvent: MutableSharedFlow<Long?> = MutableSharedFlow()
    val openSearchBinScreenEvent = _openSearchBinScreenEvent.asSharedFlow()

    fun onNewSearchClicked() {
        viewModelScope.launch {
            _openSearchBinScreenEvent.emit(null)
        }
    }

    fun onBinHistoryItemClicked(binId: Long) {
        viewModelScope.launch {
            _openSearchBinScreenEvent.emit(binId)
        }
    }


}