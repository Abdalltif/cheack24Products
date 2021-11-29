package com.abdalltif.check24.presentation.fragments.products_list

import androidx.lifecycle.*
import com.abdalltif.check24.domain.use_cases.get_products.GetProductsUseCase
import com.abdalltif.check24challenge.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProductsListViewModel
@Inject constructor (
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    private val _state = MutableLiveData<ProductsListState>()
    val state: LiveData<ProductsListState> = _state

    init {
        getProducts()
    }

    fun getProducts() {
        getProductsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> { _state.value = ProductsListState(products = result.data ?: emptyList()) }
                is Resource.Error<*> -> { _state.value = ProductsListState(error = result.message ?: "An error occurred!") }
                is Resource.Loading -> { _state.value = ProductsListState(isLoading = true) }
            }
        }.launchIn(viewModelScope)
    }
}