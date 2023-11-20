package com.example.pokemon_challenge.repository

import com.example.pokemon_challenge.api.ApiService
import com.example.pokemon_challenge.api.ApiServiceCustom
import com.example.pokemon_challenge.request.InsertPokemonRequest
import com.example.pokemon_challenge.request.PokemonRequest
import com.example.pokemon_challenge.response.CatchPokemonResponse
import com.example.pokemon_challenge.response.InsertPokemonResponse
import retrofit2.Response
import javax.inject.Inject

class RemoteRepositoryApiCustom @Inject constructor(private val apiServiceCustom: ApiServiceCustom) {
    suspend fun catchPokemon(pokemonRequest: PokemonRequest): Response<CatchPokemonResponse> = apiServiceCustom.catchPokemon(pokemonRequest)
    suspend fun insertPokemon(insertPokemonRequest: InsertPokemonRequest): Response<InsertPokemonResponse> = apiServiceCustom.insertPokemon(insertPokemonRequest)

}