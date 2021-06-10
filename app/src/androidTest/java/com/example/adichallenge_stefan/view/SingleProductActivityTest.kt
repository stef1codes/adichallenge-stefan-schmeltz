package com.example.adichallenge_stefan.view

import org.junit.Assert.*
import org.junit.Test

class SingleProductActivityTest{
    @Test
    fun `When is EditText Empty`() {
        // return true if the user hasn't written a review before sending
        val result = ReviewViewModel(ReviewRepository()).isEditTextEmpty("")
        assertEquals(true,result)

    }
    @Test
    fun `When is not EditText Empty`() {
        // return true if the user hasn't written a review before sending
        val result = ReviewViewModel(ReviewRepository()).isEditTextEmpty("Cool product!!!")
        assertEquals(false,result)

    }

    @Test
    fun `When Product is Not Rated`() {
        // return true if the product is'nt rated yet before sending a review
        val result = ReviewViewModel(ReviewRepository()).isProductNotRated(0)
        assertEquals(true,result)

    }

}