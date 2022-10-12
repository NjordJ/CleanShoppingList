package com.iruda.cleanshoppinglist.domain.repositories

import com.iruda.cleanshoppinglist.domain.entities.ShopItem

interface ShopListRepository {

    fun addShopItem(shopItem: ShopItem)

    fun updateShopItem(shopItem: ShopItem)

    fun deleteShopItem(shopItem: ShopItem)

    fun getShopList(): List<ShopItem>

    fun getShopItem(shopItemId: Int): ShopItem
}