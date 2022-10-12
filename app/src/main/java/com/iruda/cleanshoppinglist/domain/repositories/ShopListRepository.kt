package com.iruda.cleanshoppinglist.domain.repositories

import androidx.lifecycle.LiveData
import com.iruda.cleanshoppinglist.domain.entities.ShopItem

interface ShopListRepository {

    fun addShopItem(shopItem: ShopItem)

    fun updateShopItem(shopItem: ShopItem)

    fun deleteShopItem(shopItem: ShopItem)

    fun getShopList(): LiveData<List<ShopItem>>

    fun getShopItem(shopItemId: Int): ShopItem
}