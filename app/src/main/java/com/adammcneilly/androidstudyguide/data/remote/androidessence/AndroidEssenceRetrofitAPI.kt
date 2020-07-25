package com.adammcneilly.androidstudyguide.data.remote.androidessence

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import retrofit2.http.GET

interface AndroidEssenceRetrofitAPI {
    @GET("feed.xml")
    suspend fun getFeed(): AndroidEssenceFeed

    companion object {
        private const val BASE_URL = "https://androidessence.com"

        fun getDefaultApi(): AndroidEssenceRetrofitAPI {
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .client(client)
                .build()
                .create(AndroidEssenceRetrofitAPI::class.java)
        }
    }
}
