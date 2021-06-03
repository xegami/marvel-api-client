package com.xegami.mac.rest.controller

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.xegami.mac.MyApp
import com.xegami.mac.R
import com.xegami.mac.rest.RetrofitInstance
import com.xegami.mac.rest.dto.CharacterDataWrapperDTO
import com.xegami.mac.rest.event.ErrorEvent
import com.xegami.mac.rest.event.GetCharacterByIdEvent
import com.xegami.mac.rest.event.GetCharactersEvent
import com.xegami.mac.rest.operation.CharacterOperations
import org.apache.commons.codec.digest.DigestUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val PUBLIC_KEY = "3d1aacb48a233b3240bbe529f27914ca"
private const val PRIVATE_KEY = "cc5a35c72c14f7f844858700924e9ceccf2b1ba2"

class CharacterController private constructor() {

    companion object {
        val instance = CharacterController()
    }

    private val characterOperations = RetrofitInstance.instance.create(CharacterOperations::class.java)
    private val resources = MyApp.instance.resources

    fun getCharacters(limit: Int) {
        val ts = System.currentTimeMillis()

        characterOperations.getCharacters(limit, ts, PUBLIC_KEY, generateHash(ts)).enqueue(object : Callback<JsonElement> {
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                if (!response.isSuccessful) {
                    ErrorEvent(resources.getString(R.string.error_get_characters)).post()
                    return
                }

                val jsonObject = response.body()?.asJsonObject
                val wrapper = Gson().fromJson(jsonObject, CharacterDataWrapperDTO::class.java)
                GetCharactersEvent(wrapper.data.results).post()
            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                ErrorEvent(resources.getString(R.string.error_call)).post()
            }

        })
    }

    fun getCharacterById(id: Int) {
        val ts = System.currentTimeMillis()

        characterOperations.getCharacterById(id, ts, PUBLIC_KEY, generateHash(ts)).enqueue(object : Callback<JsonElement> {
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                if (!response.isSuccessful) {
                    ErrorEvent(resources.getString(R.string.error_get_character_details)).post()
                    return
                }

                val jsonObject = response.body()?.asJsonObject
                val wrapper = Gson().fromJson(jsonObject, CharacterDataWrapperDTO::class.java)
                GetCharacterByIdEvent(wrapper.data.results[0]).post()
            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                ErrorEvent(resources.getString(R.string.error_call)).post()
            }

        })
    }

    private fun generateHash(ts: Long): String {
        return DigestUtils.md5Hex((ts.toString() + PRIVATE_KEY + PUBLIC_KEY).toByteArray())
    }

}