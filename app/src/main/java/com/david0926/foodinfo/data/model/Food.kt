package com.david0926.foodinfo.data.model

import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class Food(
    val manufacture: String,
    val rnum: String,
    val prdkind: String,
    val productGb: String,
    val rawmtrl: String,
    val imgurl1: String,
    val imgurl2: String,
    val prdlstReportNo: String,
    val prdkindstate: String,
    val allergy: String,
    val prdlstNm: String,
) : Serializable