package com.example.adichallenge_stefan

import com.example.adichallenge_stefan.retrofit.ApiResponse
import retrofit2.Response
class Utils {


    inline fun <T> safeApiCall(apiCall: () -> Response<T>): ApiResponse<T> {
    return try {
        ApiResponse.success(apiCall.invoke())
    } catch (e: Exception) {
        ApiResponse.failure(e)
    }

}

fun isEditTextEmpty(text: String): Boolean = text.isEmpty()

fun isProductNotRated(numStars: Int): Boolean = numStars == 0

}