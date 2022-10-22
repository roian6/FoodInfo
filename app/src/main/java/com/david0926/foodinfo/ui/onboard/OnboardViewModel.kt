package com.david0926.foodinfo.ui.onboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OnboardViewModel : ViewModel() {

    private val _currentPage = MutableLiveData(0)
    val currentPage: LiveData<Int>
        get() = _currentPage

    fun setCurrentPage(page: Int) {
        _currentPage.value = page
    }

}