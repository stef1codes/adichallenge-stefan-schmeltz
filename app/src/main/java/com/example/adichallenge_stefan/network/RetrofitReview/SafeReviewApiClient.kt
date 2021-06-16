package com.example.adichallenge_stefan.network.RetrofitReview

import com.example.adichallenge_stefan.network.ApiResponse
import com.example.adichallenge_stefan.safeApiCall

class SafeReviewApiClient(private val reviewService: ReviewService) {

    suspend fun getSingleProducts(id: String): ApiResponse<List<Review>> {
        return safeApiCall { reviewService.getSingleProductReview(id) }
    }

    suspend fun sendReviewForProducts(
        id: String,
        review: String,
        rating: Int
    ): ApiResponse<Review> {
        return safeApiCall { reviewService.sendReview(id, rating, review) }
    }



}