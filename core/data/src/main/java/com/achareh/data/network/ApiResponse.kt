package com.achareh.data.network

sealed class ApiResponse<T> {

    data class SuccessResponse<T>(val body: T?) : ApiResponse<T>()

    data class ErrorResponse<T>(val code: Int, val message: String?) : ApiResponse<T>()

}
