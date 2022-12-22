package com.tadiuzzz.binchecker.ui.searchBin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tadiuzzz.binchecker.databinding.ItemBinHistoryBinding
import com.tadiuzzz.binchecker.domain.model.BinInfo

class BinHistoryViewHolder(val binding: ItemBinHistoryBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: BinInfo) {
        binding.tvBinNumber.text = item.bin
    }

    companion object {
        fun create(parent: ViewGroup): BinHistoryViewHolder {
            val binding = ItemBinHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return BinHistoryViewHolder(binding)
        }
    }

}