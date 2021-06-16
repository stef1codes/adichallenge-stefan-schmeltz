package com.example.adichallenge_stefan

import org.junit.Assert.*
import org.junit.Test

class UtilsTest{
    @Test
    fun `When  EditText is Empty`() {
        // return true if the user hasn't written a review before sending
        val result = checkIfTextIsEmpty("")
        assertEquals(true,result)

    }
    @Test
    fun `When  EditText is not Empty`() {
        // return true if the user hasn't written a review before sending
        val result = checkIfTextIsEmpty("Cool product!!!")
        assertEquals(false,result)

    }

    @Test
    fun `When Product is Not Rated`() {
        // return true if the product is'nt rated yet before sending a review
        val result = checkIfProductNotRated(0)
        assertEquals(true,result)

    }

    @Test
    fun `When Product is Rated`() {
        // return true if the product is'nt rated yet before sending a review
        val result2 = checkIfProductNotRated(1)
        assertEquals(false,result2)
    }
}