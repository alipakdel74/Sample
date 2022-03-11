package com.achareh.data.network

enum class HttpStatusCode(val code: Int, val description: String, val message: String? = null) {
    ERROR_UNKNOWN(-1,"ERROR_UNKNOWN"),
    OK(200,"OK"),
    Created(201,"Created"),
    BadRequest(400,"BadRequest"),
    Unauthorized(401,"Unauthorized");

    companion object{
        private val map = values().associateBy(HttpStatusCode::code)
        fun fromInt(type: Int) = map[type] ?: ERROR_UNKNOWN
    }
}
