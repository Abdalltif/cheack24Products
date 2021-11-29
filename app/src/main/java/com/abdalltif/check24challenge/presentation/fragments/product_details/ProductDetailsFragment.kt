package com.abdalltif.check24.presentation.fragments.product_details

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import coil.transform.RoundedCornersTransformation
import com.abdalltif.check24.domain.entities.product.Product
import com.abdalltif.check24.presentation.fragments.products_list.ProductState
import com.abdalltif.check24.presentation.fragments.products_list.ProductsListFragmentDirections
import com.abdalltif.check24.presentation.fragments.products_list.ProductsListState
import com.abdalltif.check24.presentation.fragments.products_list.ProductsListViewModel
import com.abdalltif.check24challenge.R
import com.abdalltif.check24challenge.databinding.FragmentProductDetailsBinding
import com.abdalltif.check24challenge.presentation.fragments.product_details.ProductDetailsViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsFragment : Fragment(R.layout.fragment_product_details), View.OnClickListener {
    private val viewModel: ProductDetailsViewModel by viewModels()
    private lateinit var binding: FragmentProductDetailsBinding
    val args: ProductDetailsFragmentArgs by navArgs()
    lateinit var product: Product

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        product = args.product

        binding = DataBindingUtil.setContentView<FragmentProductDetailsBinding>(requireActivity(), R.layout.fragment_product_details).apply {
            this.mProduct = product
            this.lifecycleOwner = requireActivity()
        }

        binding.imageView.load(product.imageURL) {
            placeholder(R.drawable.ic_ph_image)
            crossfade(true)
            transformations(RoundedCornersTransformation(5f))
        }

        binding.btnFavorite.setOnClickListener(this)
        binding.textFooter.setOnClickListener(this)

        viewModel.getProductState(product.id.toString())
        viewModel.state.observe(requireActivity(), productStateObserver)

        return view
    }

    private val productStateObserver = Observer<ProductState> { state ->
        if (state.isFavorited) {
            binding.btnFavorite.text = getString(R.string.unfavorie)
            binding.textName.setTextColor(Color.BLUE)
        }

    }

    override fun onClick(view: View?) {
        when(view?.id){
            binding.btnFavorite.id -> viewModel.setFavoritedProduct(product.id.toString())
            binding.textFooter.id -> goToWebview()
        }
    }

    private fun goToWebview() {
        val action = ProductsListFragmentDirections.actionProductsFragmentToWebviewFragment()
        findNavController().navigateUp()
        findNavController().navigate(action)
    }
}