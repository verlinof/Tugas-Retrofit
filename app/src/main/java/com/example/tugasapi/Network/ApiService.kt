package com.example.tugasapi.Network

import com.example.tugasapi.Models.CharacterModels
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("character")
    fun getCharacter(): Call<CharacterModels>
}