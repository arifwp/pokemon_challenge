package com.example.pokemon_challenge.request

import com.google.gson.annotations.SerializedName

data class PokemonRequest (
    @SerializedName("pokemon_id")
    var pokemon_id: String,

    @SerializedName("name")
    var name: String
)