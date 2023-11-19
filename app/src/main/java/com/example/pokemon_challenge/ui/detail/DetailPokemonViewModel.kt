package com.example.pokemon_challenge.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon_challenge.repository.RemoteRepository
import com.example.pokemon_challenge.response.MovesResponse
import com.example.pokemon_challenge.response.TypesResponse
import com.example.pokemon_challenge.utils.BaseResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailPokemonViewModel @Inject constructor(private val repository: RemoteRepository)  : ViewModel() {

    private val TAG = "DetailVM"

    private val _dataMoves = MutableLiveData<BaseResponse<MovesResponse>>()
    val dataMoves: LiveData<BaseResponse<MovesResponse>> = _dataMoves

    private val _dataTypes = MutableLiveData<BaseResponse<TypesResponse>>()
    val dataTypes: LiveData<BaseResponse<TypesResponse>> = _dataTypes

    fun getMoves(id: String){
        viewModelScope.launch {
            try {

                Log.d(TAG, "id: ${id.toString()}")
                val response = repository.getMoves(id)
                if (response.code() == 200){
                    _dataMoves.value = BaseResponse.Success(response.body())
                } else {
                    _dataMoves.value = BaseResponse.Error(response.message().toString())
                }
            } catch (e: Exception){
                _dataMoves.value = BaseResponse.Error(e.message.toString())
            }
        }
    }

    fun getTypes(id: String){
        viewModelScope.launch {
            try {

                val response = repository.getTypes(id)
                if(response.code() == 200){
                    _dataTypes.value = BaseResponse.Success(response.body())
                } else {
                    Log.d(TAG, "getMovesCode: ${response.code().toString()}")
                    Log.d(TAG, "getMovesErrorBody: ${response.errorBody().toString()}")
                    Log.d(TAG, "getMovesRaw: ${response.raw()}")
                    Log.d(TAG, "getMoves: ${response.message().toString()}")
                    _dataTypes.value = BaseResponse.Error(response.message().toString())
                }

            } catch(e: Exception){
                _dataTypes.value = BaseResponse.Error(e.message.toString())
            }
        }
    }

}