package com.example.tugasapi.Models

import com.google.gson.annotations.SerializedName

data class CharacterModels(
    @SerializedName("results")
    val `results`: ArrayList<CharacterData>
)

data class CharacterData(
    @SerializedName("id")
    val `id` : Int,
    @SerializedName("name")
    val `name` : String,
    @SerializedName("species")
    val `species` :String,
    @SerializedName("image")
    val `image` : String
)