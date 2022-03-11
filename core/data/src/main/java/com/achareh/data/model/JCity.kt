package com.achareh.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JCity(
    val city_id: Int,
    val city_name: String
)