package com.example.adichallenge_stefan.retrofit.RetrofitReview

import com.example.adichallenge_stefan.retrofit.ApiResponse
import retrofit2.Response

class ProductReviewApiClient (private val productReviewService: ProductReviewService){

    suspend fun getSingleProducts(id:String): ApiResponse<List<ProductReview>> {
        return safeApiCall {productReviewService.getSingleProductReview(id)}
    }

    suspend fun sendReviewForProducts(id: String, review: String, rating: Int): ApiResponse<ProductReview> {
      return safeApiCall{productReviewService.sendReview(id,rating,review)}
    }


    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): ApiResponse<T> {
        return try {
            ApiResponse.success(apiCall.invoke())
        } catch (e: Exception) {
            ApiResponse.failure(e)
        }

    }
}