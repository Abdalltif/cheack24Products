package com.abdalltif.check24.data.remote

import com.abdalltif.check24.data.remote.dto.productDto.ProductResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProductsApi {

    @GET("products-test.json")
    suspend fun getProducts(): ProductResponse

}