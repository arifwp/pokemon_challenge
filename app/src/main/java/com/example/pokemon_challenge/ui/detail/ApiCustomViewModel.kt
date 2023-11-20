package com.example.pokemon_challenge.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon_challenge.repository.RemoteRepositoryApiCustom
import com.example.pokemon_challenge.request.InsertPokemonRequest
import com.example.pokemon_challenge.request.PokemonRequest
import com.example.pokemon_challenge.response.CatchPokemonResponse
import com.example.pokemon_challenge.response.InsertPokemonResponse
import com.example.pokemon_challenge.utils.BaseResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApiCustomViewModel @Inject constructor(private val remoteRepositoryApiCustom: RemoteRepositoryApiCustom) : ViewModel() {

    private val TAG = "ApiCustomViewModel"

    private val _catchPokemon = MutableLiveData<BaseResponse<CatchPokemonResponse>>()
    val catchPokemon: LiveData<BaseResponse<CatchPokemonResponse>> = _catchPokemon

    private val _insertPokemon = MutableLiveData<BaseResponse<InsertPokemonResponse>>()
    val insertPokemon: LiveData<BaseResponse<InsertPokemonResponse>> = _insertPokemon

    fun catchingPokemon(pokemonId: String, pokemonName: String){
        viewModelScope.launch {
            try {
                val request = PokemonRequest(
                    pokemonId,
                    pokemonName
                )

                val response = remoteRepositoryApiCustom.catchPokemon(request)
                if(response.code() == 200){
                    _catchPokemon.value = BaseResponse.Success(response.body())
                } else {
                    _catchPokemon.value = BaseResponse.Error(response.body()?.message)
                }

                Log.d(TAG, "catchingPokemon: ${response.message()}")

                Log.d(TAG, "catchingPokemon: ${response.code()}")
                Log.d(TAG, "catchingPokemon: ${response.errorBody()}")
                Log.d(TAG, "catchingPokemon: ${response.raw()}")
            } catch (e: Exception){
                _catchPokemon.value = BaseResponse.Error(e.message.toString())
            }
        }
    }

    fun savePokemon(pokemonId: String, pokemonName: String, pokemonImg: String){
        viewModelScope.launch {
            try {
                val request = InsertPokemonRequest(
                    pokemonId,
                    pokemonName,
                    pokemonImg
                )
                val response = remoteRepositoryApiCustom.insertPokemon(request)
                if(response.code() == 200) {
                    _insertPokemon.value = BaseResponse.Success(response.body())
                } else {
                    _insertPokemon.value = BaseResponse.Error(response.body()?.message.toString())
                }
                Log.d(TAG, "savePokemon: ${response.message().toString()}")
                Log.d(TAG, "savePokemon: ${response.code()}")
                Log.d(TAG, "savePokemon: ${response.errorBody()}")
                Log.d(TAG, "savePokemon: ${response.raw()}")


            } catch (e: Exception){
                _insertPokemon.value = BaseResponse.Error(e.message.toString())
            }
        }
    }

}