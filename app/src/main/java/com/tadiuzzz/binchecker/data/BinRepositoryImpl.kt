package com.tadiuzzz.binchecker.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.tadiuzzz.binchecker.data.remote.BinApiInterface
import com.tadiuzzz.binchecker.domain.BinRepository
import com.tadiuzzz.binchecker.domain.model.BinInfo

class BinRepositoryImpl(
    private val binApi: BinApiInterface
): BinRepository {

    override suspend fun loadBin(bin: String) {
        val binInfo = binApi.getBinInfo(bin)
        Log.d("OLOLO", "loadBin: $binInfo")
    }

    override fun getBinHistoryList(): LiveData<BinInfo> {
        TODO("Not yet implemented")
    }

    override suspend fun getBinItem(binId: Long): BinInfo {
        TODO("Not yet implemented")
    }
}