package com.iruda.cleanshoppinglist.domain.repositories

import androidx.lifecycle.LiveData
import com.iruda.cleanshoppinglist.domain.entities.ShopItem

interface ShopListRepository {

    suspend fun addShopItem(shopItem: ShopItem)

    suspend fun updateShopItem(shopItem: ShopItem)

    suspend fun deleteShopItem(shopItem: ShopItem)

    fun getShopList(): LiveData<List<ShopItem>>

    suspend fun getShopItem(shopItemId: Int): ShopItem
}