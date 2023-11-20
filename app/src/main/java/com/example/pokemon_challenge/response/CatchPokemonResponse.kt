package com.example.pokemon_challenge.response

import com.google.gson.annotations.SerializedName

data class CatchPokemonResponse(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("new_name")
	val newName: Any? = null
)
