package com.example.adichallenge_stefan.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.adichallenge_stefan.network.RetrofitProduct.Product
import com.example.adichallenge_stefan.R
import java.util.*


class ProductListAdapter : ListAdapter<Product, ProductListAdapter.ProductViewHolder>(UserComparator()), Filterable {
    private lateinit var mListener: OnItemClickListener
    private var list = mutableListOf<Product>()

    fun setData(list: List<Product>) {
        this.list = list as MutableList<Product>
        submitList(list)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder =
        ProductViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.product_row, parent, false))

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) =
        holder.bind(getItem(position))


    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.idName)
        private val description: TextView = itemView.findViewById(R.id.idDescription)
        private val price: TextView = itemView.findViewById(R.id.idPrice)
        private var image: ImageView = itemView.findViewById(R.id.idImageView)
        private lateinit var mProduct:Product

        fun bind(currentProduct: Product) {
            mProduct         = currentProduct
            name.text        = currentProduct.product_Name
            description.text = currentProduct.product_Description
            price.text       = "${currentProduct.product_Currency}${currentProduct.product_Price}"


            Glide.with(itemView)
                .load(currentProduct.product_ImgUrl)
                .transform(CenterInside(), RoundedCorners(25))
                .placeholder(R.drawable.ic_launcher_background)
                .into(image)

        }
        init {
            itemView.setOnClickListener {
                mListener.onItemClick(
                    mProduct.product_Id,
                    mProduct.product_Name,
                    mProduct.product_Description,
                    mProduct.product_Price.toString(),
                    mProduct.product_ImgUrl,
                    mProduct.product_Currency
                )

            }
        }
    }

    class UserComparator : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.product_Name == newItem.product_Name
        }
    }

    override fun getFilter(): Filter {
        return customFilter
    }

    private val customFilter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList = mutableListOf<Product>()
            if (constraint == null || constraint.isEmpty()) {
                filteredList.addAll(list)
            } else {
                for (item in list) {
                    if (item.product_Name.toLowerCase(Locale.getDefault())
                            .contains(constraint.toString().toLowerCase(Locale.getDefault())) ||
                        item.product_Description.toLowerCase(Locale.getDefault())
                            .contains(constraint.toString().toLowerCase(Locale.ROOT))
                    ) {
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence?, filterResults: FilterResults?) {
            submitList(filterResults?.values as MutableList<Product>)
        }

    }


}



