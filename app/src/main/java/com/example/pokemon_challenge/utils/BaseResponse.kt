package com.example.pokemon_challenge.utils

sealed class BaseResponse<out T> {
    data class Success<out T>(val data: T? = null) : BaseResponse<T>()
    data class Error(val msg: String?) : BaseResponse<Nothing>()
}