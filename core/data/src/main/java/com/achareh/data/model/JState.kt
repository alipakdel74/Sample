package com.achareh.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JState(
    val state_id: Int,
    val state_name: String
)