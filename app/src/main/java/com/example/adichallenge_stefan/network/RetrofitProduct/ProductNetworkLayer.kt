package com.example.adichallenge_stefan.network.RetrofitProduct

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



object ProductNetworkLayer {

    private const val PRODUCT_BASE_URL = "http://192.168.2.10:3001"
    //private const val PRODUCT_BASE_URL = "http://localhost:3001"

    private val retrofit = Retrofit.Builder()
            .baseUrl(PRODUCT_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(OkHttpClient.Builder().build())
            .build()

    private val productService:ProductService by lazy {
        retrofit.create(ProductService::class.java)
    }

    val apiClient = ProductApiClient(productService)

}
