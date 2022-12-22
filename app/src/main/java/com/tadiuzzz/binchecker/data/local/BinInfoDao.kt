package com.tadiuzzz.binchecker.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.tadiuzzz.binchecker.data.local.model.BinInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BinInfoDao {

    @Query("SELECT * FROM bininfo")
    fun getBinInfoHistory(): Flow<List<BinInfoEntity>>

    @Query("SELEcT * FROM bininfo WHERE id = :id")
    suspend fun getBinInfoById(id: Long): BinInfoEntity?

    @Insert(onConflict = REPLACE)
    suspend fun insertBinInfo(binInfoEntity: BinInfoEntity)

}