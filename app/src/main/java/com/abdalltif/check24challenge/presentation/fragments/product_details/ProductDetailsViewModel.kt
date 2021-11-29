package com.abdalltif.check24challenge.presentation.fragments.product_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abdalltif.check24.presentation.fragments.products_list.ProductState
import com.abdalltif.check24.presentation.fragments.products_list.ProductsListState
import com.abdalltif.check24challenge.data.local.ProductsSharedPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel
@Inject constructor (
    private val productsSharedPreferences: ProductsSharedPreferences
) : ViewModel() {

    private val _state = MutableLiveData<ProductState>()
    val state: LiveData<ProductState> = _state

    fun getProductState(productId: String) {
        _state.value = ProductState(isFavorited = productsSharedPreferences.getFavorite(productId))
    }

    fun setFavoritedProduct(productId: String) {
        productsSharedPreferences.setFavorite(productId)
        _state.value = ProductState(isFavorited = true)
    }
}