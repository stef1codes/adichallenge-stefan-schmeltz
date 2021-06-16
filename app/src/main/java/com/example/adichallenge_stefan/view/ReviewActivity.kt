package com.example.adichallenge_stefan.view

import android.os.Bundle
import android.text.InputFilter
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.adichallenge_stefan.Interfaces
import com.example.adichallenge_stefan.R
import com.example.adichallenge_stefan.R.drawable
import com.example.adichallenge_stefan.R.layout
import com.example.adichallenge_stefan.Utils
import com.example.adichallenge_stefan.adapters.ReviewListAdapter
import com.example.adichallenge_stefan.repository.ReviewRepository
import com.example.adichallenge_stefan.viewmodel.reviewViewModel.ReviewViewModel
import com.example.adichallenge_stefan.viewmodel.reviewViewModel.ReviewViewModelFactory
import kotlinx.android.synthetic.main.activity_single_product.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ReviewActivity : AppCompatActivity(), Interfaces {
    private lateinit var id: String
    private lateinit var name: String
    private lateinit var image: String
    private lateinit var description: String
    private lateinit var price: String
    private lateinit var currency: String
    private lateinit var reviewAdapter: ReviewListAdapter
    private lateinit var viewModel: ReviewViewModel
    private lateinit var dialogView: View
    private lateinit var review: EditText
    private lateinit var rating: RatingBar
    private lateinit var sendReview  :Button
    private lateinit var cancelReview:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_single_product)
        // get data from ProductsActivity
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        getDataFromIntent()

        setupViewModel()

        setupReviewAdapter()

        observeData()

        setDataToUiIds()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            ReviewViewModelFactory(
                ReviewRepository()
            )
        ).get(ReviewViewModel::class.java)
        viewModel.getReviews(id)
    }

    private fun setupReviewAdapter() {
        reviewAdapter = ReviewListAdapter()
        reviewRecyclerView.adapter = reviewAdapter
        reviewRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun getDataFromIntent() {
        id = intent.getStringExtra("ID")!!
        name = intent.getStringExtra("NAME")!!
        description = intent.getStringExtra("DESCRIPTION")!!
        price = intent.getStringExtra("PRICE")!!
        image = intent.getStringExtra("IMAGE")!!
        currency = intent.getStringExtra("CURRENCY")!!


    }

    private fun setDataToUiIds() {
        review_progressbar.visibility = View.VISIBLE
        reviewRecyclerView.visibility = View.GONE
        no_ratings.visibility = View.GONE

        idProduct.text = id
        idName.text = name
        idPrice.text = "$currency$price"
        idDescription.text = description
        Glide.with(idSingleImageView)
            .load(image).transform(CenterInside(), RoundedCorners(25))
            .placeholder(drawable.ic_launcher_background)
            .into(idSingleImageView)

        dialogView = this.layoutInflater.inflate(layout.review_alertdialog, null)
        review = dialogView.findViewById<View>(R.id.leaveAReview) as EditText
        rating = dialogView.findViewById<View>(R.id.numStarReview) as RatingBar
        sendReview   = dialogView.findViewById<View>(R.id.sendReview) as Button
        cancelReview = dialogView.findViewById<View>(R.id.cancelReview) as Button
    }

    private fun observeData() = viewModel.productsLiveData.observe(this, Observer {
        if (it.isEmpty()) {
            showListIsEmptyMessage()
        } else {
            showRecyclerview()
            it?.let {
                reviewAdapter.submitList(it)
            }
        }
    })

    // if list is empty show a message
    override fun showListIsEmptyMessage() {
        review_progressbar.visibility = View.GONE
        reviewRecyclerView.visibility = View.GONE
        no_ratings.visibility = View.VISIBLE
    }

    // if list is not empty show the recyclerview with all of its reviews and ratings
    override fun showRecyclerview() {
        review_progressbar.visibility = View.GONE
        reviewRecyclerView.visibility = View.VISIBLE
        no_ratings.visibility = View.GONE
    }

    private fun showPopUpWindow() {
        val builder = AlertDialog.Builder(this)
        builder.setView(dialogView)
        val dialog = builder.create()
        dialog.show()

        // set the max of words a user can type
        review.setMaxLength(100)
        //Send customer review
        sendReview.setOnClickListener {
            when {
                // When text is empty, show a toast message
                Utils().isEditTextEmpty(review.text.toString()) ->
                    Toast.makeText(
                        this,
                        "Please write a review before sending.",
                        Toast.LENGTH_SHORT
                    ).show()
                // When product is not rated  , show a toast message
                Utils().isProductNotRated(rating.numStars) ->
                    Toast.makeText(
                        this,
                        "Please rate the product before sending ",
                        Toast.LENGTH_SHORT
                    ).show()
                else -> {
                    // send the customer's review and dismiss the dialog
                    viewModel.sendReviews(id, review.text.toString().trim(), rating.rating.toInt())
                    dialog.dismiss()
                }
            }
        }
        //cancel the alertDialog
        cancelReview.setOnClickListener {
            dialog.dismiss()
        }
    }

    fun reviewButton(view: View) {
        showPopUpWindow()
    }

    private fun EditText.setMaxLength(maxLength: Int) {
        filters = arrayOf<InputFilter>(InputFilter.LengthFilter(maxLength))
    }
}


