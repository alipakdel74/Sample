package com.achareh.data.network

enum class HttpStatusCode(val code: Int) {
    ERROR_UNKNOWN(-1),
    Success(200),
    Created(201),
    BadRequest(400),
    Unauthorized(401),
    BadGateway(502);

    companion object{
        private val map = values().associateBy(HttpStatusCode::code)
        fun fromInt(type: Int) = map[type] ?: ERROR_UNKNOWN
    }
}
