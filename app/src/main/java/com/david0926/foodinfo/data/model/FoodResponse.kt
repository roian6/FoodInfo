package com.david0926.foodinfo.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FoodResponse(
    val header: Header,
    val body: Body,
) {
    @JsonClass(generateAdapter = true)
    data class Header(
        val resultCode: String,
        val resultMessage: String,
    )

    @JsonClass(generateAdapter = true)
    data class Body(
        val totalCount: String,
        val items: List<Items>,
        val numOfRows: String,
    )

    @JsonClass(generateAdapter = true)
    data class Items(
        val item: Food,
    )
}