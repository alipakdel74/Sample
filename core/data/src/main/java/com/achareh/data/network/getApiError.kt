package com.achareh.data.network

import com.squareup.moshi.JsonDataException
import org.json.JSONException
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

fun Throwable.getApiError(): HttpStatusCode {
    return when (this) {
        is UnknownHostException, is ConnectException, is SocketException -> HttpStatusCode.ERROR_DISCONNECTED
        is JsonDataException, is JSONException -> HttpStatusCode.ERROR_PARSE
        is TimeoutException, is SocketTimeoutException -> HttpStatusCode.ERROR_TIME_OUT
        else -> HttpStatusCode.ERROR_UNKNOWN
    }
}

fun <T> Response<T>.getApiError(): HttpStatusCode = HttpStatusCode.fromInt(code())
