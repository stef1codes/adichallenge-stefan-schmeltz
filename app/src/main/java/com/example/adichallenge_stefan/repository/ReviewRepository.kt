package com.example.adichallenge_stefan.repository
import android.util.Log
import com.example.adichallenge_stefan.network.RetrofitReview.Review
import com.example.adichallenge_stefan.network.RetrofitReview.ReviewNetworkLayer


class ReviewRepository {

    suspend fun getReview(id:String): List<Review>? {
        val request = ReviewNetworkLayer.apiClient.getSingleProducts(id)
        return if(request.failed|| !request.isSuccesful) {
            Log.v("ReviewRepository", "status-failed:${request.failed} message:${request.exception} ")
            emptyList()
        } else {
            Log.v("ReviewRepository",  "status-succesful:${request.isSuccesful}")
            request.body
        }
    }

    suspend fun sendReview(id: String, review: String, rating: Int) {
       ReviewNetworkLayer.apiClient.sendReviewForProducts(id,review,rating)

    }



}