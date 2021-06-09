package com.example.adichallenge_stefan.retrofit.RetrofitReview

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ProductReviewService{

    @GET("reviews/{productId}")
    suspend fun getSingleProductReview(@Path("productId") id:String): Response<List<ProductReview>>

    @FormUrlEncoded
    @POST("reviews/{productId}")
    suspend fun sendReview(
        @Path("productId") id:String?,
        @Field("rating") rating: Int,
        @Field("text") text: String
    ): Response<ProductReview>



}