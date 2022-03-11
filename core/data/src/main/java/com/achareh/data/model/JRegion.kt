package com.achareh.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JRegion(
    val id: Int,
    val name: String,
    val city_object: JCity,
    val state_object: JState
)