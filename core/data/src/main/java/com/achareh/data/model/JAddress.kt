package com.achareh.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JAddress(
    val id: Int,
    val address_id: String,
    val region: JRegion,
    val address: String,
    val last_name: String,
    val first_name: String,
    val gender: String,
    val lat: Double,
    val lng: Double,
    val coordinate_mobile: String,
    val coordinate_phone_number: String,
) {
    fun fullName() = first_name.plus(" ").plus(last_name)
}





