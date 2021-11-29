package com.abdalltif.check24.data.remote.dto.productDto

import android.os.Parcelable
import com.abdalltif.check24.domain.entities.product.Product
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductDto(
    @SerializedName("available")
    val available: Boolean,
    @SerializedName("color")
    val color: String,
    @SerializedName("colorCode")
    val colorCode: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("imageURL")
    val imageURL: String,
    @SerializedName("longDescription")
    val longDescription: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Price,
    @SerializedName("rating")
    val rating: Double,
    @SerializedName("releaseDate")
    val releaseDate: Int,
    @SerializedName("type")
    val type: String
) : Parcelable

fun ProductDto.toProduct(): Product {
    return Product(
        id = id,
        name = name,
        price = price,
        imageURL = imageURL,
        available = available,
        rating = rating,
        color = color,
        colorCode = colorCode,
        description = description,
        longDescription = longDescription,
        releaseDate = releaseDate
    )
}