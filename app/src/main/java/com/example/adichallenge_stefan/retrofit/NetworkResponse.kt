package com.example.adichallenge_stefan.retrofit

import retrofit2.Response
import java.lang.Exception

data class ApiResponse<T>(val status: Status, val data: Response<T>?, val exception: Exception?) {

    sealed class Status
    {
        object Succes : Status()
        object Failure : Status()
    }

    companion object{
        fun <T> success(data:Response<T>):ApiResponse<T>{
            return ApiResponse(
                    status = Status.Succes,
                    data = data,
                    exception = null
            )
        }

        fun <T> failure(exception:Exception):ApiResponse<T>{
            return ApiResponse(
                    status = Status.Failure,
                    data = null,
                    exception = exception
            )
        }
    }



    val failed: Boolean get() = this.status == Status.Failure

    val isSuccesful: Boolean get() = !failed && this.data?.isSuccessful == true

    val body: T get() = this.data!!.body()!!


}