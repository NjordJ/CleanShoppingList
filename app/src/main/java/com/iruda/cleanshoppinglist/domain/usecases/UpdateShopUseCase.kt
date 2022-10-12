package com.iruda.cleanshoppinglist.domain.usecases

import com.iruda.cleanshoppinglist.domain.entities.ShopItem
import com.iruda.cleanshoppinglist.domain.repositories.ShopListRepository

class UpdateShopUseCase(private val shopListRepository: ShopListRepository) {

    fun updateShopItem(shopItem: ShopItem) {
        shopListRepository.updateShopItem(shopItem)
    }
}