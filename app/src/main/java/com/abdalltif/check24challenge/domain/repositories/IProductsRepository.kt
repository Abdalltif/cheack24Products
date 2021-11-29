package com.abdalltif.check24.domain.repositories

import com.abdalltif.check24.data.remote.dto.productDto.ProductDto

interface IProductsRepository {
    suspend fun getProducts(): List<ProductDto>
}