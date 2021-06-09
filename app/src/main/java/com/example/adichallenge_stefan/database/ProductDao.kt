package com.example.adichallenge_stefan.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_table")
data class ProductDao (
    @PrimaryKey
    val id: String,
    val currency: String,
    val description: String,
    val imgUrl: String,
    val name: String,
    val price: Int
)