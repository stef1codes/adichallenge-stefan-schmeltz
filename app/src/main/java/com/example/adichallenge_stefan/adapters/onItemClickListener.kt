package com.example.adichallenge_stefan.adapters

interface onItemClickListener {
    fun onItemClick(
        id:String,
        name: String,
        description: String,
        price: String,
        imageUrl: String,
        currency: String

    )

}

