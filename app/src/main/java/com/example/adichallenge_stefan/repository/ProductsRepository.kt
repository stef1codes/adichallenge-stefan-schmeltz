package com.example.adichallenge_stefan.repository
import android.util.Log
import com.example.adichallenge_stefan.retrofit.ApiResponse
import com.example.adichallenge_stefan.retrofit.RetrofitProduct.*


class ProductsRepository() {

    suspend fun getProducts(): List<Product>? {
        val request = ProductNetworkLayer.apiClient.getProducts()
        if(request.failed|| !request.isSuccesful){
            return emptyList()
        }else {
            return request.body
        }
    }

}