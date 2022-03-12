package com.achareh.data.repository

import com.achareh.data.model.JAddress
import com.achareh.data.model.body.BAddress
import com.achareh.data.network.ApiExecutor
import com.achareh.data.network.ResultResponse
import com.achareh.data.network.service.AcharehApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AcharehRepoImpl(private val api: AcharehApi) : AcharehRepo, ApiExecutor() {

    override fun addressList(): Flow<ResultResponse<JAddress>> = flow {
        emitApiResponse {
            api.addressList()
        }
    }

    override fun createAddress(address: BAddress): Flow<ResultResponse<JAddress>> = flow {
        emitApiResponse {
            api.createAddress(address)
        }
    }
}