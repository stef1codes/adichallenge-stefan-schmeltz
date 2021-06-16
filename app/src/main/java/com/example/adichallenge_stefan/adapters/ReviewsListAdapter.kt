package com.example.adichallenge_stefan.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.adichallenge_stefan.R
import com.example.adichallenge_stefan.network.RetrofitReview.Review


class ReviewListAdapter : ListAdapter<Review, ReviewListAdapter.ProductViewHolder>(UserComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder =
        ProductViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.review_row, parent, false)
        )

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) =
        holder.bind(getItem(position))


    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val description = itemView.findViewById<TextView>(R.id.reviewDescription)
        private val ratingBar = itemView.findViewById<RatingBar>(R.id.reviewRatingBar)
        fun bind(pr: Review) {
            description.text = if (!pr.text.isNullOrEmpty()) pr.text else "No review available"
            ratingBar.rating = pr.rating.toFloat()
        }
    }

    class UserComparator : DiffUtil.ItemCallback<Review>() {
        override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
            return oldItem.productId == newItem.productId
        }
    }

}



