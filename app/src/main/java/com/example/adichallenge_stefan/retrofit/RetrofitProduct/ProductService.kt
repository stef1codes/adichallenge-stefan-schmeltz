package com.example.adichallenge_stefan.retrofit.RetrofitProduct

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductService{

    @GET("/product")
    suspend fun getProducts(): Response<List<Product>>


}




