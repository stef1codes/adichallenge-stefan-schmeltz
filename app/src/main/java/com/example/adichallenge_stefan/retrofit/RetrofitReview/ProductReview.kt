package com.example.adichallenge_stefan.retrofit.RetrofitReview


import com.google.gson.annotations.SerializedName

data class ProductReview(
        @SerializedName("locale") val locale: String,
        @SerializedName("productId") val productId: String,
        @SerializedName("rating") val rating: Int,
        @SerializedName("text") val text: String
)