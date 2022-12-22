package com.tadiuzzz.binchecker.domain

import com.tadiuzzz.binchecker.domain.model.BinInfo
import kotlinx.coroutines.flow.Flow

interface BinRepository {
    suspend fun loadBin(bin: String): BinInfo
    fun getBinHistoryList(): Flow<List<BinInfo>>
    suspend fun getBinItem(binId: Long): BinInfo?
    suspend fun saveBinHistoryItem(binInfo: BinInfo, bin: String)
    suspend fun deleteHistoryItem(binId: Long)
}