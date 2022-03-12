package com.achareh.sample.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.achareh.data.model.JAddress
import com.achareh.sample.databinding.ItemAddressBinding

class AddressItem(private val data: JAddress) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        AddressHolder(ItemAddressBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is AddressHolder)
            holder.fetchData(data)
    }

    override fun getItemCount() = 1

    class AddressHolder(private val binding: ItemAddressBinding) : ViewHolder(binding.root) {
        fun fetchData(address: JAddress) {
            binding.txtAddress.text = address.address
            binding.txtRegion.text = address.fullName()
        }
    }
}