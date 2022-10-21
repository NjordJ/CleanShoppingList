package com.iruda.cleanshoppinglist.data.db

import com.iruda.cleanshoppinglist.data.models.ShopItemDbModel
import com.iruda.cleanshoppinglist.domain.entities.ShopItem

class ShopListMapper {

    fun mapEntityToDbModel(shopItem: ShopItem) = ShopItemDbModel(
        id = shopItem.id,
        name = shopItem.name,
        count = shopItem.count,
        isActive = shopItem.isActive
    )

    fun mapDbModelToEntity(shopItemDbModel: ShopItemDbModel) = ShopItem(
        id = shopItemDbModel.id,
        name = shopItemDbModel.name,
        count = shopItemDbModel.count,
        isActive = shopItemDbModel.isActive
    )

    fun mapListDbModelToListEntity(list: List<ShopItemDbModel>) = list.map {
        mapDbModelToEntity(it)
    }
}
