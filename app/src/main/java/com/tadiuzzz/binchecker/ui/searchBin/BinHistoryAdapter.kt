package com.tadiuzzz.binchecker.ui.searchBin

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tadiuzzz.binchecker.domain.model.BinInfo

class BinHistoryAdapter(
    private val onItemClick: (item: BinInfo) -> Unit,
    private val onDeleteClick: (item: BinInfo) -> Unit,
) : ListAdapter<BinInfo, BinHistoryViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BinHistoryViewHolder.create(parent).apply {
            binding.root.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    getItem(adapterPosition)?.let(onItemClick)
                }
            }
            binding.ivDelete.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    getItem(adapterPosition)?.let(onDeleteClick)
                }
            }
        }

    override fun onBindViewHolder(holder: BinHistoryViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<BinInfo>() {
            override fun areItemsTheSame(oldItem: BinInfo, newItem: BinInfo) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: BinInfo, newItem: BinInfo) =
                oldItem == newItem
        }
    }
}