package com.tadiuzzz.binchecker.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.tadiuzzz.binchecker.BuildConfig
import com.tadiuzzz.binchecker.data.BinRepositoryImpl
import com.tadiuzzz.binchecker.data.remote.BinApiInterface
import com.tadiuzzz.binchecker.domain.BinRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val SERVER_ADDRESS = "https://lookup.binlist.net"
private const val CONNECT_TIMEOUT: Long = 120
private const val WRITE_TIMEOUT: Long = 300
private const val READ_TIMEOUT: Long = 120

private const val HEADER_AUTHORIZATION = "Authorization"
private const val HEADER_BEARER = "Bearer"
private const val HEADER_X_OS = "X-OS"
private const val HEADER_X_BUILD = "X-Build"
private const val HEADER_CONTENT_TYPE = "Content-Type"
private const val HEADER_DEVICE_TYPE = "Device-Type"

val networkKoinModule = module {
    singleOf(::BinRepositoryImpl) bind BinRepository::class
    single<OkHttpClient> { provideOkHttpClient() }
    single<BinApiInterface> {
        createRetrofitInstance(get())
            .create(BinApiInterface::class.java)
    }
}

private fun createRetrofitInstance(
    okHttpClient: OkHttpClient,
): Retrofit = Retrofit.Builder()
    .baseUrl(SERVER_ADDRESS)
    .addConverterFactory(GsonConverterFactory.create(createGsonInstance()))
    .client(okHttpClient)
    .build()

private fun createGsonInstance(): Gson {
    val builder = GsonBuilder()
        .setLenient()
    return builder.create()
}

private fun provideOkHttpClient(): OkHttpClient {
    val builder = OkHttpClient.Builder()
    builder
        .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)

    if (BuildConfig.DEBUG) {
        builder.addInterceptor(getHttpLoggingInterceptor())
    }
    return builder.build()
}

private fun getHttpLoggingInterceptor() = HttpLoggingInterceptor()
    .apply {
        level = HttpLoggingInterceptor.Level.BODY
    }