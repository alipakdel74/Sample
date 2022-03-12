package com.achareh.data.model.body

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class BAddress(
    val address: String,
    val last_name: String,
    val first_name: String,
    val gender: String,
    val lat: Double,
    val lng: Double,
    val coordinate_mobile: String,
    val coordinate_phone_number: String,
    val region: String = "1",
) : Parcelable





