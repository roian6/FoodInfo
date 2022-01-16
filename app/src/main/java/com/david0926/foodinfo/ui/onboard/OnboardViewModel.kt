package com.david0926.foodinfo.ui.onboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.viewpager2.widget.ViewPager2

class OnboardViewModel : ViewModel() {
    val currentPage = MutableLiveData(0)
    var maxLength = 0

    var pagerCallback: ViewPager2.OnPageChangeCallback =
        object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                currentPage.value = position
            }
        }
}