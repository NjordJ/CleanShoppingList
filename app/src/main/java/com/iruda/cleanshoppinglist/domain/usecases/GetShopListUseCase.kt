package com.iruda.cleanshoppinglist.domain.usecases

import com.iruda.cleanshoppinglist.domain.entities.ShopItem
import com.iruda.cleanshoppinglist.domain.repositories.ShopListRepository

class GetShopListUseCase(private val shopListRepository: ShopListRepository) {

    fun getShopList(): List<ShopItem> {
        return shopListRepository.getShopList()
    }
}