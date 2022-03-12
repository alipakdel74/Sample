package com.achareh.sample.viewModel

import androidx.lifecycle.viewModelScope
import com.achareh.component.viewModel.BaseViewModel
import com.achareh.data.model.JAddress
import com.achareh.data.model.body.BAddress
import com.achareh.data.network.ResultResponse
import com.achareh.data.repository.AcharehRepo
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class AddressViewModel(
    private val repo: AcharehRepo,
) : BaseViewModel() {

    private val _addressList = MutableSharedFlow<ResultResponse<JAddress>>()
    val addressList = _addressList.asSharedFlow()

    fun getAddressList() {
        repo.addressList().onEach {
            _addressList.emit(it)
        }.launchIn(viewModelScope)
    }

    private val _createAddress = MutableSharedFlow<ResultResponse<JAddress>>()
    val createAddress = _createAddress.asSharedFlow()

    fun createAddress(address: BAddress) {
        repo.createAddress(address).onEach {
            _createAddress.emit(it)
        }.launchIn(viewModelScope)
    }

}