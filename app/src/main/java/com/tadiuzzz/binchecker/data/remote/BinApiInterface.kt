package com.tadiuzzz.binchecker.data.remote

import com.tadiuzzz.binchecker.domain.model.BinInfo
import retrofit2.http.GET
import retrofit2.http.Path

interface BinApiInterface {

    @GET("/{bin}")
    suspend fun getBinInfo(
        @Path("bin") bin: String
    ): BinInfo


}