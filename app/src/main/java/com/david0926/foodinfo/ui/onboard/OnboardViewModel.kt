package com.david0926.foodinfo.ui.onboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.viewpager2.widget.ViewPager2

class OnboardViewModel : ViewModel() {

    private val _currentPage = MutableLiveData(0)
    val currentPage: LiveData<Int>
        get() = _currentPage

    var pagerCallback: ViewPager2.OnPageChangeCallback =
        object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                _currentPage.value = position
            }
        }
}