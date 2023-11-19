package com.example.pokemon_challenge.api

import com.example.pokemon_challenge.response.MovesResponse
import com.example.pokemon_challenge.response.PokemonResponse
import com.example.pokemon_challenge.response.TypesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @Headers("Content-Type: application/json")
    @GET("pokemon?limit=limit")
    suspend fun getPokemon(@Query("limit") limit: String): Response<PokemonResponse>

    @Headers("Content-Type: application/json")
    @GET("move/{id}")
    suspend fun getMoves(@Path("id") id: String): Response<MovesResponse>

    @Headers("Content-Type: application/json")
    @GET("type/{id}")
    suspend fun getTypes(@Path("id") id: String): Response<TypesResponse>

}