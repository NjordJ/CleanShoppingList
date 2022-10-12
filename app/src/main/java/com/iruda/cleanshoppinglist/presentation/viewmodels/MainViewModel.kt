package com.iruda.cleanshoppinglist.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iruda.cleanshoppinglist.data.repositories.ShopListRepositoryImpl
import com.iruda.cleanshoppinglist.domain.entities.ShopItem
import com.iruda.cleanshoppinglist.domain.usecases.DeleteShopUseCase
import com.iruda.cleanshoppinglist.domain.usecases.GetShopListUseCase
import com.iruda.cleanshoppinglist.domain.usecases.UpdateShopUseCase

class MainViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopUseCase = DeleteShopUseCase(repository)
    private val updateShopUseCase = UpdateShopUseCase(repository)

    val shopList = MutableLiveData<List<ShopItem>>()

    fun getShopList() {
        val list = getShopListUseCase.getShopList()
        shopList.postValue(list)
    }

    fun deleteShopItem(shopItem: ShopItem) {
        val list = deleteShopUseCase.deleteShopItem(shopItem)
        getShopList()
    }

    fun changeEnabledStateShopItem(shopItem: ShopItem) {
        val newItem = shopItem.copy(isActive = !shopItem.isActive)
        updateShopUseCase.updateShopItem(newItem)
        getShopList()
    }

}