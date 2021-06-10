package com.example.adichallenge_stefan.retrofit.RetrofitProduct

import com.example.adichallenge_stefan.Utils
import com.example.adichallenge_stefan.retrofit.ApiResponse
import retrofit2.Response

class ProductApiClient(private val productService: ProductService) {

    suspend fun getProducts(): ApiResponse<List<Product>> {
        return Utils().safeApiCall { productService.getProducts() }
    }

/*
    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): ApiResponse<T> {
        return try {
            ApiResponse.success(apiCall.invoke())
        } catch (e: Exception) {
            ApiResponse.failure(e)
        }
    }*/
}