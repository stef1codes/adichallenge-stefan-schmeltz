package com.example.adichallenge_stefan.network.RetrofitProduct

import com.example.adichallenge_stefan.network.ApiResponse
import com.example.adichallenge_stefan.safeApiCall

class ProductApiClient(private val productService: ProductService) {

    // this class is just responsible for returning the Api response and the data of the response
    suspend fun getProducts(): ApiResponse<List<Product>> {
        return safeApiCall { productService.getProducts() }
    }

}