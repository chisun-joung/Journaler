package com.example.chisunjoung.journaler.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by chisunjoung on 2018. 1. 29..
 */
object BackendServiceRetrofit {

    fun obtain(
            readTimeoutInSeconds: Long =1,
            connectTimeoutInSeconds: Long = 1
    ): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return Retrofit.Builder()
                .baseUrl("http://static.milosvasic.net/jsons/journaler/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(
                        OkHttpClient
                                .Builder()
                                .addInterceptor(loggingInterceptor)
                                .readTimeout(readTimeoutInSeconds, TimeUnit.SECONDS)
                                .connectTimeout(connectTimeoutInSeconds,TimeUnit.SECONDS)
                                .build()
                )
                .build()
    }
}