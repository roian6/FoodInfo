package com.david0926.foodinfo.ui.onboard

import android.os.Bundle
import com.david0926.foodinfo.R
import com.david0926.foodinfo.base.BaseFragment
import com.david0926.foodinfo.data.model.Onboard
import com.david0926.foodinfo.databinding.FragmentOnboardBinding

class OnboardFragment : BaseFragment<FragmentOnboardBinding>(R.layout.fragment_onboard) {

    companion object {
        private const val KEY_ONBOARD = "onboard"

        @JvmStatic
        fun newInstance(onboard: Onboard) =
            OnboardFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(KEY_ONBOARD, onboard)
                }
            }
    }

    override fun init() {
        arguments?.let {
            binding.onboard = it.getSerializable(KEY_ONBOARD) as Onboard
        }
    }
}