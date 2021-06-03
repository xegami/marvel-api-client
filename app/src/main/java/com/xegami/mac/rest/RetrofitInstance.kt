package com.xegami.mac.rest

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val URL = "https://gateway.marvel.com/v1/public/"

class RetrofitInstance private constructor() {

    companion object {
        val instance = buildRetrofit()

        private fun buildRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(URL)
                .client(OkHttpClient.Builder().build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }


}