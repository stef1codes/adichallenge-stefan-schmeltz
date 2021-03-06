package com.example.adichallenge_stefan.network.RetrofitProduct

import com.google.gson.annotations.SerializedName

//this class is for handling API responses of the ProductService class
data class Product(
        @SerializedName("currency")   var product_Currency: String,
        @SerializedName("description")var product_Description: String,
        @SerializedName("id")         var product_Id: String,
        @SerializedName("imgUrl")     var product_ImgUrl: String,
        @SerializedName("name")       var product_Name: String,
        @SerializedName("price")      var product_Price: Int


)