package com.example.adichallenge_stefan.viewmodel.reviewViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adichallenge_stefan.repository.ReviewRepository
import com.example.adichallenge_stefan.retrofit.RetrofitReview.Review
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ReviewViewModel(private val repository: ReviewRepository) : ViewModel() {


    private var _reviewsLiveData = MutableLiveData<List<Review>>()
    val productsLiveData: LiveData<List<Review>> = _reviewsLiveData

    fun getReviews(id: String) {
         viewModelScope.launch { _reviewsLiveData.postValue(repository.getReview(id)) }
    }

    fun sendReviews(id: String, review: String, rating: Int) {

        viewModelScope.launch { repository.sendReview(id, review, rating) }
    }




}




