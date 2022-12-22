package com.tadiuzzz.binchecker.di

import android.app.Application
import androidx.room.Room
import com.tadiuzzz.binchecker.data.local.BinInfoDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val localDataKoinModule = module {

    single { provideNoteDatabase(androidApplication()) }

}

fun provideNoteDatabase(app: Application): BinInfoDatabase {
    return Room.databaseBuilder(
        app,
        BinInfoDatabase::class.java,
        BinInfoDatabase.DATABASE_NAME
    ).build()
}