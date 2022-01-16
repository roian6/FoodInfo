package com.david0926.foodinfo.ui.food

import android.content.Intent
import android.os.Bundle
import com.david0926.foodinfo.R
import com.david0926.foodinfo.base.BaseActivity
import com.david0926.foodinfo.data.model.Food
import com.david0926.foodinfo.databinding.ActivityFoodBinding
import com.david0926.foodinfo.ui.all.ViewPagerAdapter

class FoodActivity : BaseActivity<ActivityFoodBinding>(R.layout.activity_food) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val food = intent.getSerializableExtra("food") as Food
        binding.food = food

        val fragments = listOf(
            FoodImageFragment.newInstance(food.imgurl1),
            FoodImageFragment.newInstance(food.imgurl2)
        )

        binding.vpFood.adapter = ViewPagerAdapter(this, fragments)
        binding.vpFood.offscreenPageLimit = fragments.size - 1

        binding.toolbarFood.setNavigationOnClickListener { finish() }
        binding.btFoodShare.setOnClickListener { shareFood(food) }
    }

    private fun shareFood(food: Food) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "[${food.prdlstNm}](${food.imgurl1})\n${food.rawmtrl}")
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, getString(R.string.food_share))
        startActivity(shareIntent)
    }
}