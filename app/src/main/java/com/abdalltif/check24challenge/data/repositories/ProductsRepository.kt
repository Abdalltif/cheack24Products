package com.abdalltif.check24challenge.data.repositories

import com.abdalltif.check24.data.remote.ProductsApi
import com.abdalltif.check24.data.remote.dto.productDto.ProductDto
import com.abdalltif.check24.data.remote.dto.productDto.ProductResponse
import com.abdalltif.check24.domain.repositories.IProductsRepository
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class ProductsRepository @Inject constructor(
        private val productsApi: ProductsApi
    ) : IProductsRepository {

    override suspend fun getProducts(): List<ProductDto> {
        val response = productsApi.getProducts()
        return response.productDtos
    }

}