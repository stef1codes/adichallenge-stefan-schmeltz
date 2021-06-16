package com.example.adichallenge_stefan.network.RetrofitReview

import retrofit2.Response
import retrofit2.http.*

interface ReviewService{

    @GET("reviews/{productId}")
    suspend fun getSingleProductReview(@Path("productId") id:String): Response<List<Review>>

    @FormUrlEncoded
    @POST("reviews/{productId}")
    suspend fun sendReview(
        @Path("productId") id:String?,
        @Field("rating") rating: Int,
        @Field("text") text: String
    ): Response<Review>



}