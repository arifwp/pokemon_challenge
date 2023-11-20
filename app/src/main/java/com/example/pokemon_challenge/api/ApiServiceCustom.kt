package com.example.pokemon_challenge.api

import com.example.pokemon_challenge.request.InsertPokemonRequest
import com.example.pokemon_challenge.request.PokemonRequest
import com.example.pokemon_challenge.response.CatchPokemonResponse
import com.example.pokemon_challenge.response.InsertPokemonResponse
import com.example.pokemon_challenge.response.PokemonResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiServiceCustom {

    @Headers("Content-Type: application/json")
    @POST("pokemon")
    suspend fun catchPokemon(@Body pokemonRequest: PokemonRequest): Response<CatchPokemonResponse>

    @Headers("Content-Type: application/json")
    @POST("pokemon/insert")
    suspend fun insertPokemon(@Body insertPokemonRequest: InsertPokemonRequest): Response<InsertPokemonResponse>


}