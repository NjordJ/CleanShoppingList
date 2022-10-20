package com.iruda.cleanshoppinglist.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.iruda.cleanshoppinglist.R
import com.iruda.cleanshoppinglist.databinding.ItemShopDisabledBinding
import com.iruda.cleanshoppinglist.databinding.ItemShopEnabledBinding
import com.iruda.cleanshoppinglist.domain.entities.ShopItem

class ShopListAdapter :
    ListAdapter<ShopItem, ShopItemViewHolder>(ShopItemDiffCallback()) {

    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
    var onShopItemClickListener: ((ShopItem) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ShopItemViewHolder {
        val layout = when (viewType) {
            VIEW_TYPE_ACTIVE -> R.layout.item_shop_enabled
            VIEW_TYPE_NON_ACTIVE -> R.layout.item_shop_disabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val binding =
            DataBindingUtil.inflate<ViewDataBinding>(
                LayoutInflater.from(parent.context),
                layout,
                parent,
                false
            )
        return ShopItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val item = getItem(position)
        val binding = holder.binding
        when (binding) {
            is ItemShopDisabledBinding -> {
                binding.shopItem = item
            }
            is ItemShopEnabledBinding -> {
                binding.shopItem = item
            }
        }
        binding.root.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(item)
            true
        }
        binding.root.setOnClickListener {
            onShopItemClickListener?.invoke(item)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.isActive) VIEW_TYPE_ACTIVE else VIEW_TYPE_NON_ACTIVE
    }

    companion object {
        const val VIEW_TYPE_ACTIVE = 100
        const val VIEW_TYPE_NON_ACTIVE = 101

        const val MAX_POOL_SIZE = 25
    }
}