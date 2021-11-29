package com.abdalltif.check24.data.repository

import com.abdalltif.check24.data.remote.dto.productDto.ProductDto
import com.abdalltif.check24.domain.repositories.IProductsRepository

class FakeProductsRepository constructor(private val products: List<ProductDto>) : IProductsRepository {

    override suspend fun getProducts(): List<ProductDto> {
        return products
    }

}