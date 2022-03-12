package com.achareh.data.repository

import com.achareh.data.model.JAddress
import com.achareh.data.model.body.BAddress
import com.achareh.data.network.ResultResponse
import kotlinx.coroutines.flow.Flow

interface AcharehRepo {

    fun addressList(): Flow<ResultResponse<JAddress>>

    fun createAddress(address: BAddress): Flow<ResultResponse<JAddress>>

}