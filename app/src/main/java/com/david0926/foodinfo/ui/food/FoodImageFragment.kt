package com.david0926.foodinfo.ui.food

import android.os.Bundle
import com.david0926.foodinfo.R
import com.david0926.foodinfo.ui.common.BaseFragment
import com.david0926.foodinfo.databinding.FragmentFoodImageBinding

class FoodImageFragment : BaseFragment<FragmentFoodImageBinding>(R.layout.fragment_food_image) {

    companion object {
        private const val KEY_IMAGE_LINK = "imageLink"

        @JvmStatic
        fun newInstance(image: String) =
            FoodImageFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_IMAGE_LINK, image)
                }
            }
    }

    override fun init() {
        arguments?.let {
            binding.image = it.getString(KEY_IMAGE_LINK)
        }
    }
}