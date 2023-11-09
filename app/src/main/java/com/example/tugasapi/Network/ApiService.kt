package com.example.tugasapi.Network

import com.example.tugasapi.Models.CharacterData
import com.example.tugasapi.Models.CharacterModels
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("character")
    fun getCharacter(): Call<CharacterModels>

    @GET("character/{id}")
    fun getCharacterById(@Path("id") id: Int): Call<CharacterData>
}