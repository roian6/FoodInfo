package com.david0926.foodinfo.ui.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.david0926.foodinfo.data.model.Food
import com.david0926.foodinfo.databinding.RowFoodBinding
import com.david0926.foodinfo.ui.food.FoodActivity

class MainRecyclerHolder(private val binding: RowFoodBinding) :
    RecyclerView.ViewHolder(binding.root) {

    constructor(parent: ViewGroup) : this(
        RowFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    init {
        binding.root.setOnClickListener {
            it.context.run {
                startActivity(Intent(this, FoodActivity::class.java).apply {
                    putExtra("food", binding.food)
                })
            }
        }
    }

    fun bind(food: Food) {
        binding.food = food
    }
}