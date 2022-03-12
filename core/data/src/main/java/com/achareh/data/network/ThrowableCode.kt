package com.achareh.data.network

enum class ThrowableCode(val code: Int) {
    ERROR_UNKNOWN(-1),
    ERROR_TIME_OUT(-2),
    ERROR_DISCONNECTED(-3),
    ERROR_PARSE(-4),
}