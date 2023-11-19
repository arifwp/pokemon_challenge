package com.example.pokemon_challenge.repository

import com.example.pokemon_challenge.api.ApiService
import com.example.pokemon_challenge.response.MovesResponse
import com.example.pokemon_challenge.response.PokemonResponse
import com.example.pokemon_challenge.response.TypesResponse
import retrofit2.Response
import javax.inject.Inject

class RemoteRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getPokemon(limit: String): Response<PokemonResponse> = apiService.getPokemon(limit)
    suspend fun getMoves(id: String): Response<MovesResponse> = apiService.getMoves(id)
    suspend fun getTypes(id: String): Response<TypesResponse> = apiService.getTypes(id)
}