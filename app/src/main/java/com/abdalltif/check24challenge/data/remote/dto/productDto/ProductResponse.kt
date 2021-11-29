package com.abdalltif.check24.data.remote.dto.productDto


import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("filters")
    val filters: List<String>,
    @SerializedName("header")
    val header: Header,
    @SerializedName("products")
    val productDtos: List<ProductDto>
)