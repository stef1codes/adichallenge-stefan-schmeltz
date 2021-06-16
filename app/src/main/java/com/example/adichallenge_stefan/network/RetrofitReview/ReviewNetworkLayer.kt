package com.example.adichallenge_stefan.network.RetrofitReview

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ReviewNetworkLayer {

    private const val PRODUCT_BASE_URL = "http://localhost:3002/"
    private val retrofit = Retrofit.Builder()
            .baseUrl(PRODUCT_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(OkHttpClient.Builder().build())
            .build()

    private val SERVICE: ReviewService by lazy {
        retrofit.create(ReviewService::class.java)
    }

    val apiClient = ReviewApiClient(SERVICE)

}
