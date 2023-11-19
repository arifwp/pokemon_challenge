package com.example.pokemon_challenge.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon_challenge.repository.RemoteRepository
import com.example.pokemon_challenge.response.PokemonResponse
import com.example.pokemon_challenge.utils.BaseResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor (private val repository: RemoteRepository) : ViewModel() {

    private val _listPokemon = MutableLiveData<BaseResponse<PokemonResponse>>()
    val listPokemon: LiveData<BaseResponse<PokemonResponse>> = _listPokemon

    fun getListPokemon(limit: String){
        viewModelScope.launch {
            try {
                val response = repository.getPokemon(limit)
                if(response.code() == 200){
                    _listPokemon.value = BaseResponse.Success(response.body())
                } else {
                    _listPokemon.value = BaseResponse.Error(response.message().toString())
                }
            } catch (e: Exception){
                _listPokemon.value = BaseResponse.Error(e.message.toString())
            }
        }
    }

}