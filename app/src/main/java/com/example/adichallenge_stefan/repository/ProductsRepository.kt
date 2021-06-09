package com.example.adichallenge_stefan.repository
import android.util.Log
import com.example.adichallenge_stefan.retrofit.RetrofitProduct.*


class ProductsRepository() {

    suspend fun getProducts(): List<Product>? {
        val request = ProductNetworkLayer.apiClient.getProducts()
        if(request.failed|| !request.isSuccesful){
            Log.v("TAG-getReview","---------------")
            Log.v("TAG-getProducts","${request.exception}")
            Log.v("TAG-getProducts","has failed? ${request.failed}")
            Log.v("TAG-getProducts","succesful? ${request.isSuccesful}")
            return null }
        return request.body
    }

    suspend fun getSingleProduct(id:String): Product?{
        val request = ProductNetworkLayer.apiClient.getSingleProducts(id)
        if(request.isSuccessful){
            return request.body()
        }
        return null
    }


}