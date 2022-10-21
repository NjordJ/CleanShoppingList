package com.iruda.cleanshoppinglist.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.iruda.cleanshoppinglist.data.repositories.ShopListRepositoryImpl
import com.iruda.cleanshoppinglist.domain.entities.ShopItem
import com.iruda.cleanshoppinglist.domain.usecases.DeleteShopUseCase
import com.iruda.cleanshoppinglist.domain.usecases.GetShopListUseCase
import com.iruda.cleanshoppinglist.domain.usecases.UpdateShopUseCase
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ShopListRepositoryImpl(application)

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopUseCase = DeleteShopUseCase(repository)
    private val updateShopUseCase = UpdateShopUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun deleteShopItem(shopItem: ShopItem) {
        viewModelScope.launch {
            deleteShopUseCase.deleteShopItem(shopItem)
        }
    }

    fun changeEnabledStateShopItem(shopItem: ShopItem) {
        viewModelScope.launch {
            val newItem = shopItem.copy(isActive = !shopItem.isActive)
            updateShopUseCase.updateShopItem(newItem)
        }
    }
}