package com.abdalltif.check24.presentation.fragments.products_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.abdalltif.check24.presentation.fragments.products_list.adapters.ProductsAdapter
import com.abdalltif.check24.domain.entities.product.Product
import com.abdalltif.check24challenge.R
import com.abdalltif.check24challenge.databinding.FragmentProductsBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsListFragment : Fragment(R.layout.fragment_products), ProductsAdapter.OnProductItemClickListener, View.OnClickListener {
    private lateinit var binding: FragmentProductsBinding
    private val viewModel: ProductsListViewModel by viewModels()
    private val productAdapter by lazy { ProductsAdapter(this) }
    lateinit var productsList: List<Product>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.setContentView<FragmentProductsBinding>(requireActivity(), R.layout.fragment_products).apply {
            this.lifecycleOwner = requireActivity()
        }

        binding.textFooter.setOnClickListener(this)

        setupRecyclerView()
        setupPullToRefresh()

        viewModel.state.observe(requireActivity(), productsListStateObserver)

        return view
    }

    private val productsListStateObserver = Observer<ProductsListState> { state ->
        binding.swipe.isRefreshing = state.isLoading

        val visibility = if (state.error.isNotBlank()) View.VISIBLE else View.GONE
        binding.errorImageView.visibility = visibility

        if (state.error.isNotBlank())
            binding.root.snackBar(state.error, Snackbar.LENGTH_LONG)

        productsList = state.products
        productAdapter.setProductsList(productsList)
    }

    private fun setupPullToRefresh() {
        binding.swipe.setOnRefreshListener {
            viewModel.getProducts()
        }
    }

    private fun setupRecyclerView(){
        val recyclerView = binding.recyclerProucts
        recyclerView.apply {
            adapter = productAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    fun View.snackBar(message: String, duration: Int = Snackbar.LENGTH_LONG){
        Snackbar.make(this, message, duration).show()
    }

    override fun onProductItemClick(position: Int) {
        val action = ProductsListFragmentDirections.actionNavigateToDetailsFragment( productsList[position] )
        findNavController().navigate(action)
    }

    private fun goToWebview() {
        val action = ProductsListFragmentDirections.actionProductsFragmentToWebviewFragment()
        findNavController().navigateUp()
        findNavController().navigate(action)
    }

    override fun onClick(view: View?) {
        when(view?.id){
            binding.textFooter.id -> goToWebview()
        }
    }
}