package com.abdalltif.check24.domain.use_cases.get_products

import com.abdalltif.check24.data.remote.dto.productDto.toProduct
import com.abdalltif.check24.domain.entities.product.Product
import com.abdalltif.check24.domain.repositories.IProductsRepository
import com.abdalltif.check24challenge.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repository: IProductsRepository
){
    operator fun invoke(): Flow<Resource<List<Product>>> = flow {
        try {
            emit(Resource.Loading<List<Product>>())
            val products = repository.getProducts().map { it.toProduct() }
            emit(Resource.Success<List<Product>>(products))
        } catch (e: HttpException){
            emit(Resource.Error<List<Product>>(message = e.localizedMessage ?: "An unexpected Error!"))
        } catch (e: IOException){
            emit(Resource.Error<List<Product>>(message = e.localizedMessage ?: "Network Error!"))
        }
    }
}