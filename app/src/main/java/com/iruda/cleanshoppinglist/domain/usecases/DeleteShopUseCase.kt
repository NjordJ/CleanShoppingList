package com.iruda.cleanshoppinglist.domain.usecases

import com.iruda.cleanshoppinglist.domain.entities.ShopItem
import com.iruda.cleanshoppinglist.domain.repositories.ShopListRepository

class DeleteShopUseCase(private val shopListRepository: ShopListRepository) {

    fun deleteShopItem(shopItem: ShopItem) {
        shopListRepository.deleteShopItem(shopItem)
    }
}