package com.example.adichallenge_stefan.repository
import com.example.adichallenge_stefan.retrofit.RetrofitProduct.Product
import com.example.adichallenge_stefan.retrofit.RetrofitProduct.ProductNetworkLayer


class ProductsRepository {

    suspend fun getProducts(): List<Product>? {
        val request = ProductNetworkLayer.apiClient.getProducts()
        return if(request.failed|| !request.isSuccesful){
            emptyList()
        }else {
            request.body
        }
    }

}