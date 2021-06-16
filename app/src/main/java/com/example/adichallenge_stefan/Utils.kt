package com.example.adichallenge_stefan

import android.content.Context
import android.text.InputFilter
import android.widget.EditText
import android.widget.Toast
import com.example.adichallenge_stefan.network.ApiResponse
import retrofit2.Response


inline fun <T> safeApiCall(apiCall: () -> Response<T>): ApiResponse<T> {
    return try {
        ApiResponse.success(apiCall.invoke())
    } catch (e: Exception) {
        ApiResponse.failure(e)
    }
}

fun checkIfTextIsEmpty(text: String): Boolean {
    return text.isEmpty()
}

fun checkIfProductNotRated(numStars: Int): Boolean = numStars == 0


fun showMessage(context: Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}
 fun EditText.setMaxLength(maxLength: Int) {
    filters = arrayOf<InputFilter>(InputFilter.LengthFilter(maxLength))
}