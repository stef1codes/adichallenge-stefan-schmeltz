package com.example.adichallenge_stefan.repository
import com.example.adichallenge_stefan.Utils
import com.example.adichallenge_stefan.retrofit.RetrofitReview.Review
import com.example.adichallenge_stefan.retrofit.RetrofitReview.ReviewNetworkLayer


class ReviewRepository() {

    suspend fun getReview(id:String): List<Review>? {
        val request = ReviewNetworkLayer.apiClient.getSingleProducts(id)
        return if(request.failed|| !request.isSuccesful) emptyList<Review>() else request.body
    }

    suspend fun sendReview(id: String, review: String, rating: Int) {
       ReviewNetworkLayer.apiClient.sendReviewForProducts(id,review,rating)

    }



}