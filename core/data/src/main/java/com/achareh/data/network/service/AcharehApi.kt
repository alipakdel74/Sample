package com.achareh.data.network.service

import com.achareh.data.model.JAddress
import com.achareh.data.model.body.BAddress
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AcharehApi {

    @POST("api/karfarmas/address")
    suspend fun createAddress(@Body address: BAddress): Response<JAddress>

    @POST("api/karfarmas/address")
    suspend fun addressList(): Response<JAddress>

}