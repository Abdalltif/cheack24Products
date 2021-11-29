package com.abdalltif.check24.domain.entities.product

import android.os.Parcelable
import com.abdalltif.check24.data.remote.dto.productDto.Price
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    val id: Int,
    val name: String,
    val price: Price,
    val imageURL: String,
    val available: Boolean,
    val rating: Double,
    val color: String,
    val colorCode: String,
    val description: String,
    val longDescription: String,
    val releaseDate: Int,
) : Parcelable