package com.achareh.data.network

sealed class ResultResponse<out T> {

    object Loading : ResultResponse<Nothing>()

    data class Success<out T>(val result: T?) : ResultResponse<T>()

    data class Error(val code: Int, val message: String?) : ResultResponse<Nothing>()

}