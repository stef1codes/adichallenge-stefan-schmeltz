package com.example.adichallenge_stefan.repository
import android.util.Log
import com.example.adichallenge_stefan.network.ApiResponse
import com.example.adichallenge_stefan.network.RetrofitProduct.Product
import com.example.adichallenge_stefan.network.RetrofitProduct.ProductNetworkLayer


class ProductsRepository {

    /** In this class the function 'getProducts' will determine what to send back to the viewmodel.
      If the request failed or is not successful,it will send a empty list back to the viewmodel.
     **/
    suspend fun getProducts(): List<Product>? {
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