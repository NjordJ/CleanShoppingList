package com.iruda.cleanshoppinglist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.iruda.cleanshoppinglist.R
import com.iruda.cleanshoppinglist.domain.entities.ShopItem
import com.iruda.cleanshoppinglist.presentation.adapters.ShopListAdapter
import com.iruda.cleanshoppinglist.presentation.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private lateinit var adapter: ShopListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this@MainActivity) {
            adapter.shopList = it
        }
    }

    private fun setupRecyclerView() {
        val recyclerViewShopList = findViewById<RecyclerView>(R.id.recycler_view_shop_list)
        adapter = ShopListAdapter()
        recyclerViewShopList.adapter = adapter
    }

}