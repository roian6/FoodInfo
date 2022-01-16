package com.david0926.foodinfo.ui.onboard

import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2

@BindingAdapter("bindViewPagerCallback")
fun bindPagerCallback(pager: ViewPager2, callback: ViewPager2.OnPageChangeCallback) {
    pager.registerOnPageChangeCallback(callback)
}