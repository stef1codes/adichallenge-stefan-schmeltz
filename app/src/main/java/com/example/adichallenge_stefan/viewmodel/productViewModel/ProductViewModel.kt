package com.example.adichallenge_stefan.viewmodel.productViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adichallenge_stefan.network.RetrofitProduct.Product
import com.example.adichallenge_stefan.repository.ProductsRepository
import kotlinx.coroutines.launch

class ProductViewModel(private val repository: ProductsRepository) : ViewModel() {

    private var _productsLiveData = MutableLiveData<List<Product>>()
    val productsLiveData: LiveData<List<Product>> = _productsLiveData

    val searchText: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    init {
        viewModelScope.launch { _productsLiveData.postValue(repository.getProducts())}

    }





}




