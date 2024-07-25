package com.david0926.foodinfo.data.remote

import com.david0926.foodinfo.data.model.FoodResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodService {
    @GET("/B553748/CertImgListServiceV2/getCertImgListServiceV2")
    suspend fun getFoods(
        @Query("ServiceKey") ServiceKey: String?,
        @Query("prdlstReportNo") prdlstReportNo: String?,
        @Query("prdlstNm") prdlstNm: String?,
        @Query("returnType") returnType: String?,
        @Query("pageNo") pageNo: String?,
        @Query("numOfRows") numOfRows: String?
    ): Response<FoodResponse>
}