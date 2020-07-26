package com.xegami.excibit.rest.operation

import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterOperations {

    @GET("characters")
    fun getCharacters(@Query("limit") limit: Int, @Query("ts") ts: Long, @Query("apikey") apikey: String, @Query("hash") hash: String): Call<JsonElement>

    @GET("characters/{id}")
    fun getCharacterById(@Path("id") id: Int, @Query("ts") ts: Long, @Query("apikey") apikey: String, @Query("hash") hash: String): Call<JsonElement>
}