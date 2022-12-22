package com.tadiuzzz.binchecker.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tadiuzzz.binchecker.data.local.model.BinInfoEntity

@Database(
    entities = [BinInfoEntity::class],
    version = 1
)
abstract class BinInfoDatabase : RoomDatabase() {

    abstract val binInfoDao: BinInfoDao

    companion object {
        const val DATABASE_NAME = "bininfo_db"
    }
}