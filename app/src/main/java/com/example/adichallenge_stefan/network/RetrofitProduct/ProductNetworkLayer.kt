package com.example.adichallenge_stefan.network.RetrofitProduct

import com.example.adichallenge_stefan.safeApiCall
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



object ProductNetworkLayer {

/**  Creation of  the Retrofit service instance . **/
    private const val PRODUCT_BASE_URL = "http://localhost:3001"
    private val retrofit = Retrofit.Builder()
            .baseUrl(PRODUCT_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(OkHttpClient.Builder().build())
            .build().create(ProductService::class.java)

    val apiClient = SafeProductApiClient(retrofit)

}
