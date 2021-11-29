package com.abdalltif.check24challenge.data.local

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.util.Log
import androidx.core.content.edit
import com.abdalltif.check24challenge.common.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductsSharedPreferences @Inject constructor(@ApplicationContext context : Context) {
    private val prefs = context.getSharedPreferences(Constants.PRODUCTS_PREFS, MODE_PRIVATE)

    fun getFavorite(productId: String): Boolean {
        return prefs.getBoolean(productId, false)
    }

    fun setFavorite(productId: String) {
        prefs.edit {
            putBoolean(productId, true)
            apply()
        }
    }

}