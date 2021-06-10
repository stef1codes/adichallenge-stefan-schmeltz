package com.example.adichallenge_stefan.retrofit.RetrofitReview

import com.example.adichallenge_stefan.Utils
import com.example.adichallenge_stefan.retrofit.ApiResponse

class ReviewApiClient(private val reviewService: ReviewService) {

    suspend fun getSingleProducts(id: String): ApiResponse<List<Review>> {
        return Utils().safeApiCall { reviewService.getSingleProductReview(id) }
    }

    suspend fun sendReviewForProducts(
        id: String,
        review: String,
        rating: Int
    ): ApiResponse<Review> {
        return Utils().safeApiCall { reviewService.sendReview(id, rating, review) }
    }



}