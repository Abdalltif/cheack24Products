package com.abdalltif.check24.data.remote.dto.productDto


import com.google.gson.annotations.SerializedName

data class Header(
    @SerializedName("headerDescription")
    val headerDescription: String,
    @SerializedName("headerTitle")
    val headerTitle: String
)