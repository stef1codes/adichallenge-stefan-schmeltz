package com.example.adichallenge_stefan.viewmodel.reviewViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adichallenge_stefan.repository.ReviewRepository
import com.example.adichallenge_stefan.retrofit.RetrofitReview.ProductReview
import kotlinx.coroutines.launch

class ReviewViewModel(private val repository: ReviewRepository) : ViewModel() {


    private var _reviewsLiveData = MutableLiveData<List<ProductReview>>()
    val productsLiveData: LiveData<List<ProductReview>> = _reviewsLiveData

    fun getReviews(id: String) {
        viewModelScope.launch { _reviewsLiveData.postValue(repository.getReview(id)) }
    }

    fun sendReviews(id: String, review: String, rating: Int) {

        viewModelScope.launch { repository.sendReview(id, review, rating) }
    }

    fun isEditTextEmpty(text: String): Boolean = text.isEmpty()

    fun isProductNotRated(numStars: Int): Boolean = numStars == 0


}




