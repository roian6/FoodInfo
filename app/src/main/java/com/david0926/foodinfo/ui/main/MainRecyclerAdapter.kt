package com.david0926.foodinfo.ui.main

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.david0926.foodinfo.data.model.Food

class MainRecyclerAdapter : ListAdapter<Food, MainRecyclerHolder>(MainDiffUtilCallback()) {

    class MainDiffUtilCallback : DiffUtil.ItemCallback<Food>() {
        override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean =
            oldItem.prdlstReportNo == newItem.prdlstReportNo

        override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRecyclerHolder {
        return MainRecyclerHolder(parent)
    }

    override fun onBindViewHolder(holder: MainRecyclerHolder, position: Int) {
        holder.bind(getItem(position))
    }
}