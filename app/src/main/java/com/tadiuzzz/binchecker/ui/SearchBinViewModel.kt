package com.tadiuzzz.binchecker.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tadiuzzz.binchecker.domain.BinRepository
import kotlinx.coroutines.launch

class SearchBinViewModel(
    private val repository: BinRepository
) : ViewModel() {

    private val _binText: MutableLiveData<String> = MutableLiveData("")
    val binText: LiveData<String> = _binText

    fun onBinTextChanged(text: String) {
        if (binText.value != text) {
            _binText.value = text
        }
    }

    fun onSearchBtnClicked() {
        viewModelScope.launch {
            kotlin.runCatching {
                val bin = binText.value!!
                repository.loadBin(bin)
            }.onFailure {
                Log.d("OLOLO", "ОШИБКА")
            }
        }
    }


}