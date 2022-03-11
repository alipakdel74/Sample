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

    protected suspend fun <T> FlowCollector<ApiResponse<T>>.emitApiResponse(request: suspend () -> Response<T>) {
        emit(ResultResponse.Loading)

        val apiResponse = executeApi(request)
        emit(apiResponse)
    }

    private suspend fun <T> executeApi(request: suspend () -> Response<T>): ApiResponse<T> {

        val response: ApiResponse<T> = try {
            create(request())
        } catch (t: Throwable) {
            t.printStackTrace()
            create(t)
        }

        return when (response) {
            is ApiResponse.SuccessResponse -> ApiResponse.SuccessResponse(response.body)
            is ApiResponse.ErrorResponse -> ApiResponse.ErrorResponse(response.code, response.message)
        }
    }

    private fun <T> create(error: Throwable): ApiResponse.ErrorResponse<T> =
         when (error) {
            is UnknownHostException, is ConnectException, is SocketException -> ApiResponse.ErrorDisconnected
            is JsonDataException, is JSONException -> ApiResponse.ErrorParse
            is TimeoutException, is SocketTimeoutException -> ApiResponse.ErrorTimeOut
            else -> HttpStatusCode.ErrorUnKnown
        }

    private fun <T> create(response: Response<T>): ApiResponse<T> {
        return if (response.isSuccessful) {
            if (response.code() == HttpStatusCode.Created.code)
                ApiResponse.SuccessResponse(response.body())
            else ApiResponse.ErrorResponse(response.getApiError().code, response.message())
        } else {
            if (response.code() == HttpStatusCode.Unauthorized.code) {
                val errorBytes = response.errorBody()?.bytes()
                if (errorBytes != null) {
                    val errorContent = String(errorBytes)

                    val jsonAdapter = Moshi.Builder().build().adapter(JResponseError::class.java)
                    val errorResponse = jsonAdapter.fromJson(errorContent)
                    if (errorResponse != null)
                        ApiResponse.ErrorResponse(
                            response.getApiError().code,
                            errorResponse.detail.orEmpty()
                        )
                    else {
                        ApiResponse.ErrorResponse(
                            response.getApiError().code,
                            response.message()
                        )
                    }
                } else {
                    ApiResponse.ErrorResponse(
                        response.getApiError().code,
                        response.message()
                    )
                }
            } else
                ApiResponse.ErrorResponse(
                    response.getApiError().code,
                    response.message()
                )

        }
    }
}