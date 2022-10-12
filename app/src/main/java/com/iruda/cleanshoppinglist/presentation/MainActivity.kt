package com.iruda.cleanshoppinglist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.iruda.cleanshoppinglist.R
import com.iruda.cleanshoppinglist.domain.entities.ShopItem
import com.iruda.cleanshoppinglist.presentation.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private lateinit var linearLayoutShopList: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        linearLayoutShopList = findViewById(R.id.linear_layout_shop_list)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this@MainActivity) {
            showList(it)
        }
    }

    private fun showList(list: List<ShopItem>) {
        linearLayoutShopList.removeAllViews()
        for (shopItem in list) {
            val layoutId =
                if (shopItem.isActive) R.layout.item_shop_enabled else R.layout.item_shop_disabled
            val view = LayoutInflater.from(this@MainActivity)
                .inflate(layoutId, linearLayoutShopList, false)
            val textViewName = view.findViewById<TextView>(R.id.text_view_name)
            val textViewCount = view.findViewById<TextView>(R.id.text_view_count)
            textViewName.text = shopItem.name
            textViewCount.text = shopItem.count.toString()
            view.setOnLongClickListener {
                viewModel.changeEnabledStateShopItem(shopItem)
                true
            }
            linearLayoutShopList.addView(view)
        }
    }
}