package com.example.adichallenge_stefan.viewmodel.reviewViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.adichallenge_stefan.repository.ReviewRepository

class ReviewViewModelFactory(private val repository: ReviewRepository ):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReviewViewModel::class.java)) {
            return ReviewViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class..")    }
}