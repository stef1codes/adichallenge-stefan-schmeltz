package com.example.adichallenge_stefan.retrofit.RetrofitReview

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ProductReviewNetworkLayer {
    private const val PRODUCT_BASE_URL = "http://192.168.2.10:3002/"
    private val retrofit = Retrofit.Builder()
            .baseUrl(PRODUCT_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(OkHttpClient.Builder().build())
            .build()

    private val productService: ProductReviewService by lazy {
        retrofit.create(ProductReviewService::class.java)
    }

    val apiClient = ProductReviewApiClient(productService)

}
