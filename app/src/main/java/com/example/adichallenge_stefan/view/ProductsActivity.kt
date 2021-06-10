package com.example.adichallenge_stefan.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adichallenge_stefan.Interfaces
import com.example.adichallenge_stefan.R
import com.example.adichallenge_stefan.viewmodel.productViewModel.ProductViewModel
import com.example.adichallenge_stefan.viewmodel.productViewModel.ProductViewModelFactory
import com.example.adichallenge_stefan.repository.ProductsRepository

import com.example.adichallenge_stefan.adapters.ProductListAdapter
import com.example.adichallenge_stefan.adapters.onItemClickListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_single_product.*

class ProductsActivity : AppCompatActivity(), Interfaces {

    private lateinit var productAdapter: ProductListAdapter
    private lateinit var searchView: SearchView
    private lateinit var viewModel: ProductViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setUpdUiIds()

        setupViewModel()

        setupAdapter()

        observeData()

        clickOnItemAdapter()
    }

    private fun setupAdapter() {
        productAdapter = ProductListAdapter()
        idRecycleview.adapter = productAdapter
        idRecycleview.layoutManager = LinearLayoutManager(this)
    }

    fun setUpdUiIds(){
        idRecycleview.visibility = View.VISIBLE
        no_data.visibility = View.GONE
    }
    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            ProductViewModelFactory(
                ProductsRepository()
            )
        ).get(ProductViewModel::class.java)
        viewModel.getProducts()

    }

    private fun observeData() = viewModel.productsLiveData.observe(this, Observer {
        if (it.isEmpty()) {
            showListIsEmptyMessage()
        } else {
            showRecyclerview()
            it?.let { productAdapter.setData(it) }
        }
    })


    private fun clickOnItemAdapter() {
        productAdapter.setOnitemClickListener(object : onItemClickListener {
            override fun onItemClick(
                id: String,
                name: String,
                description: String,
                price: String,
                imageUrl: String,
                currency: String
            ) {
                val intent = Intent(this@ProductsActivity, SingleProductActivity::class.java)
                intent.putExtra("ID",id)
                intent.putExtra("NAME",name)
                intent.putExtra("DESCRIPTION",description)
                intent.putExtra("PRICE",price)
                intent.putExtra("IMAGE",imageUrl)
                intent.putExtra("CURRENCY",currency)
                Log.v("TAG-onItemClick", "$id - $name - $description - $price - $currency")
                startActivity(intent)
            }
        })
    }

    override fun showListIsEmptyMessage() {
        idRecycleview.visibility = View.GONE
        no_data.visibility = View.VISIBLE
    }

    // if list is not empty show the recycleview with all of its reviews and ratings
    override fun showRecyclerview() {
        idRecycleview.visibility = View.VISIBLE
        no_data.visibility = View.GONE
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val search = menu?.findItem(R.id.search)
        searchView = (search?.actionView as? SearchView)!!
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    viewModel.searchText.value = newText
                    productAdapter.filter.filter(viewModel.searchText.value);
                }
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }


}


