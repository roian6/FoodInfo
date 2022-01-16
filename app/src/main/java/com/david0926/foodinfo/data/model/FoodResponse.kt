package com.david0926.foodinfo.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FoodResponse(
    val pageNo: String,
    val resultCode: String,
    val totalCount: String,
    val list: List<Food>,
    val resultMessage: String,
    val numOfRows: String,
)