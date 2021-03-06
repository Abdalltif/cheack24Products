package com.abdalltif.check24challenge.domain.interaction.get_products

import com.abdalltif.check24.data.remote.dto.productDto.Price
import com.abdalltif.check24.data.remote.dto.productDto.ProductDto
import com.abdalltif.check24.data.repository.FakeProductsRepository
import com.abdalltif.check24.domain.entities.product.Product
import com.abdalltif.check24.domain.use_cases.get_products.GetProductsUseCase
import com.abdalltif.check24challenge.common.Resource
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetProductsUseCaseTest {
    private lateinit var getProductsUseCase: GetProductsUseCase
    private lateinit var fakeRepository: FakeProductsRepository
    private lateinit var products: MutableList<ProductDto>

    @Before
    fun setup() {
        products = mutableListOf<ProductDto>()
        fakeRepository = FakeProductsRepository(products)
        getProductsUseCase = GetProductsUseCase(fakeRepository)
    }

    @Test
    fun `get products is alwaays in loading state at first emit`() = runBlocking {
        assertThat(getProductsUseCase().first()).isInstanceOf(Resource.Loading::class.java)
    }

    @Test
    fun `get products is success state, is true`() = runBlocking {
        assertThat(getProductsUseCase().drop(1).first()).isInstanceOf(Resource.Success::class.java)
    }

    @Test
    fun `products list never return null`() = runBlocking {
        assertThat(getProductsUseCase().drop(1).first().data).isNotNull()
    }

    @Test
    fun `products list size equals to 0, is true`() = runBlocking {
        assertThat(getProductsUseCase().drop(1).first().data!!.size).isEqualTo(0)
    }

    @Test
    fun `products list size equals to 6, is true`() = runBlocking {
        for (i in 0..5) {
            products.add(i, ProductDto(true, "", "",
                "description $i", i, "", "Long description $i",
                "Product 1", Price("EUR", 34.5), 4.0,
                2021, "type $i")
            )
        }
        assertThat(getProductsUseCase().drop(1).first().data!!.size).isEqualTo(6)
    }

    @Test
    fun `is a product an entity of domain, is true`() = runBlocking {
        products.add(0, ProductDto(true, "", "",
            "description ", 1, "", "Long description",
            "Product 1", Price("EUR", 34.5), 4.0,
            2021, "type 1")
        )
        assertThat(getProductsUseCase().drop(1).first().data?.get(0)).isInstanceOf(Product::class.java)
    }

    @Test
    fun `none empty list of products, is true`() = runBlocking {
        for (i in 0..5) {
            products.add(i, ProductDto(true, "", "",
                "description $i", i, "", "Long description $i",
                "Product 1", Price("EUR", 34.5), 4.0,
                2021, "type $i")
            )
        }
        assertThat(getProductsUseCase().drop(1).first().data).isNotEmpty()
    }

    @Test
    fun `products list is empty, is true`() = runBlocking {
        assertThat(getProductsUseCase().drop(1).first().data).isEmpty()
    }
}