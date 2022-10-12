package com.iruda.cleanshoppinglist.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.iruda.cleanshoppinglist.R
import com.iruda.cleanshoppinglist.presentation.adapters.ShopListAdapter
import com.iruda.cleanshoppinglist.presentation.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private lateinit var shopListAdapter: ShopListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this@MainActivity) {
            shopListAdapter.shopList = it
        }
    }

    private fun setupRecyclerView() {
        val recyclerViewShopList = findViewById<RecyclerView>(R.id.recycler_view_shop_list)
        with(recyclerViewShopList) {
            shopListAdapter = ShopListAdapter()
            adapter = shopListAdapter
            recycledViewPool.setMaxRecycledViews(
                ShopListAdapter.VIEW_TYPE_ACTIVE,
                ShopListAdapter.MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                ShopListAdapter.VIEW_TYPE_NON_ACTIVE,
                ShopListAdapter.MAX_POOL_SIZE
            )
        }
        shopListAdapter.onShopItemLongClickListener = {
            viewModel.changeEnabledStateShopItem(it)
        }

        shopListAdapter.onShopItemClickListener = {
            Log.d("MainActivity", "ItemClick")
        }
    }

}