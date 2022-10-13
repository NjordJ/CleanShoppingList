package com.iruda.cleanshoppinglist.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.iruda.cleanshoppinglist.domain.entities.ShopItem

class ShopItemDiffCallback: DiffUtil.ItemCallback<ShopItem>() {

    override fun areItemsTheSame(
        oldItem: ShopItem,
        newItem: ShopItem
    ) = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: ShopItem,
        newItem: ShopItem
    ) = oldItem == newItem
}