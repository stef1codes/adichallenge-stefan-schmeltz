package com.example.adichallenge_stefan.viewmodel.productViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.adichallenge_stefan.repository.ProductsRepository
import java.lang.IllegalArgumentException

class ProductViewModelFactory(private val repository: ProductsRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            return ProductViewModel(
                repository
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class..")    }
}