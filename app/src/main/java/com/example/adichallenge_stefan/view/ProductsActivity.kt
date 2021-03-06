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
import com.example.adichallenge_stefan.adapters.OnItemClickListener
import com.example.adichallenge_stefan.network.RetrofitProduct.Product
import kotlinx.android.synthetic.main.activity_main.*

class ProductsActivity : AppCompatActivity(), Interfaces {

    private lateinit var productAdapter: ProductListAdapter
    private lateinit var searchView: SearchView
    private lateinit var viewModel: ProductViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /** Initialize the id from this view **/
        setUpdUiIds()

        /** Initialize the viewmodel that will call the function Getproduct that will interact with the repository class **/
        setupViewModel()

        /** Setup the recyclerviewadapter **/
        setupAdapter()

        /** Observe data coming from the viewModel. if the list is empty,
        it will not be added to the listadapter.
         If its not empty the data will be added in the list adapter **/
        observeData()

        /**
         If the user clicked on a product, It will take the information of the product
         and take to the new Activity called 'ReviewActivity' **/
        clickOnItemAdapter()

    }

    private fun setupAdapter() {
        productAdapter = ProductListAdapter()
        idRecycleview.adapter = productAdapter
        idRecycleview.layoutManager = LinearLayoutManager(this)
    }

    private fun setUpdUiIds(){
        product_circular.visibility =View.VISIBLE
        idRecycleview.visibility = View.GONE
        no_data.visibility = View.GONE
    }
    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, ProductViewModelFactory(ProductsRepository())).get(ProductViewModel::class.java)

    }

    private fun observeData() = viewModel.productsLiveData.observe(this, Observer {
        // If the product couldn't be retrieved from the api show message
        if (it.isEmpty()) {
            showListIsEmptyMessage()
        } else {
            //else set the products in the recyclerviewadapter
            showRecyclerview()
            it?.let { productAdapter.setData(it) }
        }
    })


    private fun clickOnItemAdapter() {
        productAdapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(product: Product) {
                val intent = Intent(this@ProductsActivity, ReviewActivity::class.java)
                intent.putExtra("ID",product.product_Id)
                intent.putExtra("NAME",product.product_Name)
                intent.putExtra("DESCRIPTION",product.product_Description)
                intent.putExtra("PRICE",product.product_Price.toString())
                intent.putExtra("IMAGE",product.product_ImgUrl)
                intent.putExtra("CURRENCY",product.product_Currency)
                startActivity(intent)
            }
        })
    }
  // show message after when retreiving data from api has failed
    override fun showListIsEmptyMessage() {
        product_circular.visibility = View.GONE
        idRecycleview.visibility = View.GONE
        no_data.visibility = View.VISIBLE
    }

    // if list is not empty show the recyclerview with all of its products and ratings
    override fun showRecyclerview() {
        idRecycleview.visibility = View.VISIBLE
        no_data.visibility = View.GONE
        product_circular.visibility = View.GONE

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
                // when the searchview has text then take the text and filter all the product that containts the written text.
                if (newText != null) {
                    viewModel.searchText.value = newText
                    productAdapter.filter.filter(viewModel.searchText.value)
                }
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }


}


