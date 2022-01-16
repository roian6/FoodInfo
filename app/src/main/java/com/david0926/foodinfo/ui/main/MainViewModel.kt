package com.david0926.foodinfo.ui.main

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.david0926.foodinfo.data.model.Food
import com.david0926.foodinfo.data.model.FoodRequest
import com.david0926.foodinfo.data.model.FoodResponse
import com.david0926.foodinfo.data.repository.FoodRepositoryImpl
import com.david0926.foodinfo.util.ApiKeyUtil
import com.david0926.foodinfo.util.Resource
import com.david0926.foodinfo.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val foodRepository: FoodRepositoryImpl) :
    ViewModel() {

    val result = MutableLiveData<Resource<FoodResponse>>(Resource.loading())
    val foodList = ObservableArrayList<Food>()

    val searchText = MutableLiveData("")
    var itemsPerPage = 10
    private var currentPage = 1

    init {
        getFoods()
    }

    private fun getFoods() {
        val foodRequest = FoodRequest(
            ServiceKey = ApiKeyUtil.getApiKey(),
            prdlstNm = searchText.value,
            pageNo = currentPage.toString(),
            numOfRows = itemsPerPage.toString()
        )

        viewModelScope.launch(CoroutineExceptionHandler { _, e ->
            result.value = Resource.error(e.stackTraceToString(), null)
        }) {
            result.value = Resource.loading()
            val response = withContext(Dispatchers.IO) { foodRepository.getFoods(foodRequest) }
            if (response.isSuccessful) {
                val body = response.body() ?: return@launch
                if (body.resultCode != "OK") {
                    Resource.error(body.resultMessage, body)
                    return@launch
                }

                result.value = Resource.success(body)
                if (currentPage == 1) foodList.clear()
                foodList.addAll(body.list)

            } else result.value = Resource.error(response.errorBody().toString(), null)
        }
    }

    fun refreshFoods() {
        currentPage = 1
        getFoods()
    }

    fun getMoreFoods() {
        if (result.value!!.status == Status.LOADING) return
        currentPage += 1
        getFoods()
    }
}