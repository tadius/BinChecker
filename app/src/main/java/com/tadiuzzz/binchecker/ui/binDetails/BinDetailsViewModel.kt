package com.tadiuzzz.binchecker.ui.binDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tadiuzzz.binchecker.domain.BinRepository
import com.tadiuzzz.binchecker.ui.binDetails.model.BinDetailsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BinDetailsViewModel(
    private val binId: Long?,
    private val repository: BinRepository
) : ViewModel() {

    private val _binText: MutableStateFlow<String> = MutableStateFlow("")
    val binText = _binText.asStateFlow()

    private val _binDetailsState: MutableStateFlow<BinDetailsState> =
        MutableStateFlow(BinDetailsState.Editing)
    val binDetailsState = _binDetailsState.asStateFlow()

    init {
        binId?.let {
            viewModelScope.launch {
                val binInfo = repository.getBinItem(binId)
                binInfo?.let {
                    _binText.value = it.bin ?: ""
                    _binDetailsState.value = BinDetailsState.Success(it)
                }
            }
        }
    }

    fun onBinTextChanged(text: String) {
        _binDetailsState.value = BinDetailsState.Editing
        if (binText.value != text) {
            _binText.value = text
        }
    }

    fun onSearchBtnClicked() {
        viewModelScope.launch {
            kotlin.runCatching {
                _binDetailsState.value = BinDetailsState.Loading
                val bin = binText.value
                val binInfo = repository.loadBin(bin)
                _binDetailsState.value = BinDetailsState.Success(binInfo)
            }.onFailure {
                _binDetailsState.value = BinDetailsState.Error("Ошибка получения информации о BIN")
            }
        }
    }

    fun onMapBtnClicked() {

    }

    fun onLinkClicked() {

    }

    fun onPhoneClicked() {

    }

}