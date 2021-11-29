package com.abdalltif.check24challenge.common

import androidx.recyclerview.widget.DiffUtil
import com.abdalltif.check24.data.remote.dto.productDto.ProductDto
import com.abdalltif.check24.domain.entities.product.Product

class AppDiffUtil(
    private val oldList: List<Product>,
    private val newList: List<Product>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].id != newList[newItemPosition].id -> {
                false
            }
            oldList[oldItemPosition].id != newList[newItemPosition].id -> {
                false
            }
            else -> true
        }
    }
}