package com.example.adichallenge_stefan.network.RetrofitProduct

import com.example.adichallenge_stefan.Utils
import com.example.adichallenge_stefan.network.ApiResponse

class ProductApiClient(private val productService: ProductService) {

    // this class is just responsible for returning the Api response and the data of the response
    suspend fun getProducts(): ApiResponse<List<Product>> {
        return Utils().safeApiCall { productService.getProducts() }
    }

}