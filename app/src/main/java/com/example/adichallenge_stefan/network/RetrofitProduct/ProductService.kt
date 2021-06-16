package com.example.adichallenge_stefan.network.RetrofitProduct

import retrofit2.Response
import retrofit2.http.GET

interface ProductService{

    @GET("/product")
    suspend fun getProducts(): Response<List<Product>>


}




