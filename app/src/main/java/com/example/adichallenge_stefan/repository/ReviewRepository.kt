package com.example.adichallenge_stefan.repository
import com.example.adichallenge_stefan.retrofit.RetrofitReview.ProductReview
import com.example.adichallenge_stefan.retrofit.RetrofitReview.ProductReviewNetworkLayer


class ReviewRepository() {

    suspend fun getReview(id:String): List<ProductReview>? {
        val request = ProductReviewNetworkLayer.apiClient.getSingleProducts(id)
        if(request.failed|| !request.isSuccesful){
            return null

        }

        return request.body
    }

    suspend fun sendReview(id: String, review: String, rating: Int) {
       ProductReviewNetworkLayer.apiClient.sendReviewForProducts(id,review,rating)

    }



}