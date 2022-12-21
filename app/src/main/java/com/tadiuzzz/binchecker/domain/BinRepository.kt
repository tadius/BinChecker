package com.tadiuzzz.binchecker.domain

import androidx.lifecycle.LiveData
import com.tadiuzzz.binchecker.domain.model.BinInfo

interface BinRepository {
    suspend fun loadBin(bin: String)
    fun getBinHistoryList(): LiveData<BinInfo>
    suspend fun getBinItem(binId: Long): BinInfo
}