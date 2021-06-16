package com.example.adichallenge_stefan.repository
import android.util.Log
import com.example.adichallenge_stefan.network.ApiResponse
import com.example.adichallenge_stefan.network.RetrofitProduct.Product
import com.example.adichallenge_stefan.network.RetrofitProduct.ProductNetworkLayer


class ProductsRepository {


    suspend fun getProducts(): List<Product>? {
        // this class determine what to the if it

        val request: ApiResponse<List<Product>> = ProductNetworkLayer.apiClient.getProducts()

        return if(request.failed|| !request.isSuccesful){
            Log.v("ProductsRepository", "status-failed:${request.failed} message:${request.exception} ")
            emptyList()
        }else {
            Log.v("ProductsRepository",  "status-succesful:${request.isSuccesful}")
            request.body

        }
    }

}