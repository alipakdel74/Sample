package com.achareh.data.network

import com.achareh.data.model.JResponseError
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import kotlinx.coroutines.flow.FlowCollector
import org.json.JSONException
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

abstract class ApiExecutor {

    protected suspend fun <T> FlowCollector<ResultResponse<T>>.emitApiResponse(request: suspend () -> Response<T>) {
        emit(ResultResponse.Loading)

        val apiResponse = executeApi(request)
        emit(apiResponse)
    }

    private suspend fun <T> executeApi(request: suspend () -> Response<T>): ResultResponse<T> {

        val response: ApiResponse<T> = try {
            create(request())
        } catch (t: Throwable) {
            t.printStackTrace()
            create(t)
        }

        return when (response) {
            is ApiResponse.SuccessResponse -> ResultResponse.Success(response.body)
            is ApiResponse.ErrorResponse -> ResultResponse.Error(response.code, response.message)
        }
    }

    private fun <T> create(error: Throwable): ApiResponse.ErrorResponse<T> =
        when (error) {
            is UnknownHostException, is ConnectException, is SocketException ->
                ApiResponse.ErrorResponse(ThrowableCode.ERROR_DISCONNECTED.code, error.message)
            is JsonDataException, is JSONException ->
                ApiResponse.ErrorResponse(ThrowableCode.ERROR_PARSE.code, error.message)
            is TimeoutException, is SocketTimeoutException ->
                ApiResponse.ErrorResponse(ThrowableCode.ERROR_TIME_OUT.code, error.message)
            else -> ApiResponse.ErrorResponse(ThrowableCode.ERROR_UNKNOWN.code, error.message)
        }

    private fun <T> create(response: Response<T>): ApiResponse<T> {
        return when (response.code()) {
            HttpStatusCode.Created.code, HttpStatusCode.Success.code -> ApiResponse.SuccessResponse(response.body())
            HttpStatusCode.Unauthorized.code -> {
                var errorResponse: JResponseError? = null
                val errorBytes = response.errorBody()?.bytes()
                if (errorBytes != null) {
                    val errorContent = String(errorBytes)
                    val jsonAdapter = Moshi.Builder().build().adapter(JResponseError::class.java)
                    errorResponse = jsonAdapter.fromJson(errorContent)
                }
                ApiResponse.ErrorResponse(response.code(), errorResponse?.detail ?: response.message())
            }
            else -> ApiResponse.ErrorResponse(response.code(), HttpStatusCode.fromInt(response.code()).name)
        }
    }
}