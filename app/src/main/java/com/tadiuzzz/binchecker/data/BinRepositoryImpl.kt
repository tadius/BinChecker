package com.tadiuzzz.binchecker.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.tadiuzzz.binchecker.data.remote.BinApiInterface
import com.tadiuzzz.binchecker.domain.BinRepository
import com.tadiuzzz.binchecker.domain.model.BinInfo

class BinRepositoryImpl(
    private val binApi: BinApiInterface
): BinRepository {

    //TODO temp
    var binInfo: BinInfo? = null

    override suspend fun loadBin(bin: String): Long {
        binInfo = binApi.getBinInfo(bin)
        Log.d("OLOLO", "loadBin: $binInfo")
        return 222
    }

    override fun getBinHistoryList(): LiveData<BinInfo> {
        TODO("Not yet implemented")
    }

    override suspend fun getBinItem(binId: Long): BinInfo {
        //TODO temp
        return binInfo!!
    }
}