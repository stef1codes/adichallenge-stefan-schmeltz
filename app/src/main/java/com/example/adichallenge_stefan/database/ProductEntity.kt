package com.example.adichallenge_stefan.database

import androidx.room.PrimaryKey

data class ProductEntity (
    @PrimaryKey
    val id: String,
    val currency: String,
    val description: String,
    val imgUrl: String,
    val name: String,
    val price: Int
)