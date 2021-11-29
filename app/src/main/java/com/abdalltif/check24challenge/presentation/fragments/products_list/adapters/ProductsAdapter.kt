package com.abdalltif.check24.presentation.fragments.products_list.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.abdalltif.check24.domain.entities.product.Product
import com.abdalltif.check24challenge.R
import com.abdalltif.check24challenge.common.AppDiffUtil
import com.abdalltif.check24challenge.databinding.ProductItemBinding

class ProductsAdapter(
    private val listenerProduct: OnProductItemClickListener
) : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    private var productList = emptyList<Product>()

    inner class ProductViewHolder(val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        init {
            binding.root.setOnClickListener(this)
        }
        override fun onClick(p0: View?) {
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION)
                listenerProduct.onProductItemClick(position)
        }
    }

    // An interface to communicate with the fragment.
    interface OnProductItemClickListener {
        fun onProductItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.binding.apply {
            // pass product object to layout data.
            mProduct = productList[position]

            imageView.load(productList[position].imageURL) {
                placeholder(R.drawable.ic_ph_image)
                crossfade(true)
                transformations(RoundedCornersTransformation(5f))
            }
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun setProductsList(newList: List<Product>){
        val diffUtil = AppDiffUtil(productList, newList)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        productList  = newList
        diffResults.dispatchUpdatesTo(this)
    }
}