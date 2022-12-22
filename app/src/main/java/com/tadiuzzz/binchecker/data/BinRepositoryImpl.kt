package com.tadiuzzz.binchecker.data

import com.tadiuzzz.binchecker.data.local.BinInfoDatabase
import com.tadiuzzz.binchecker.data.local.model.BinInfoEntity
import com.tadiuzzz.binchecker.data.remote.BinApiInterface
import com.tadiuzzz.binchecker.domain.BinRepository
import com.tadiuzzz.binchecker.domain.model.BinInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest

class BinRepositoryImpl(
    private val binApi: BinApiInterface,
    private val db: BinInfoDatabase
) : BinRepository {

    override suspend fun loadBin(bin: String): BinInfo {
        val binInfo = binApi.getBinInfo(bin)
        saveBinHistoryItem(binInfo, bin)
        return binInfo
    }

    override fun getBinHistoryList(): Flow<List<BinInfo>> {
        return db.binInfoDao.getBinInfoHistory().mapLatest { list ->
            list.map {
                BinInfoEntity.mapToBinInfo(it)
            }
        }
    }

    override suspend fun getBinItem(binId: Long): BinInfo? {
        val entity = db.binInfoDao.getBinInfoById(binId) ?: return null
        return BinInfoEntity.mapToBinInfo(entity)
    }

    override suspend fun saveBinHistoryItem(binInfo: BinInfo, bin: String) {
        db.binInfoDao.insertBinInfo(
            BinInfoEntity.mapFromBinInfo(binInfo, bin)
        )
    }
}