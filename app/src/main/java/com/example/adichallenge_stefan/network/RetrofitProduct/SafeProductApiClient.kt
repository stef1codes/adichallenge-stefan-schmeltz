package com.example.adichallenge_stefan.network.RetrofitProduct

import com.example.adichallenge_stefan.network.ApiResponse
import com.example.adichallenge_stefan.safeApiCall

class SafeProductApiClient(private val productService: ProductService) {

    /** this class is  responsible for returning the Api response. the function 'getProducts'
     determines if the response is successful or a failed response
     **/
    suspend fun getProducts(): ApiResponse<List<Product>> {
        return safeApiCall { productService.getProducts() }
    }
}