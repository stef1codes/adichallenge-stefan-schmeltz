package com.example.adichallenge_stefan.network.RetrofitProduct

import retrofit2.Response
import retrofit2.http.GET
/**
 * the interface for the API call definition.
 * **/
interface ProductService{

    @GET("/product")
    suspend fun getProducts(): Response<List<Product>>


}




