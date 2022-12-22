package com.tadiuzzz.binchecker.ui.searchBin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tadiuzzz.binchecker.domain.BinRepository
import com.tadiuzzz.binchecker.domain.model.BinInfo
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BinHistoryViewModel(
    private val repository: BinRepository
) : ViewModel() {

    private val _binHistory = MutableStateFlow<List<BinInfo>>(emptyList())
    val binHistory = _binHistory.asStateFlow()

    private val _openSearchBinScreenEvent: MutableSharedFlow<Long?> = MutableSharedFlow()
    val openSearchBinScreenEvent = _openSearchBinScreenEvent.asSharedFlow()

    init {
        viewModelScope.launch {
            repository.getBinHistoryList().collect {
                _binHistory.value = it
            }
        }
    }

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