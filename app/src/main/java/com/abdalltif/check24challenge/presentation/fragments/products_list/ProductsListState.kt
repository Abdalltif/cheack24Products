package com.abdalltif.check24.presentation.fragments.products_list

import com.abdalltif.check24.domain.entities.product.Product

data class ProductsListState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val error: String = ""
)
