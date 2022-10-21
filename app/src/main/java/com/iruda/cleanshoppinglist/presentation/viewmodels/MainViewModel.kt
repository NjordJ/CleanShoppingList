package com.iruda.cleanshoppinglist.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.iruda.cleanshoppinglist.data.repositories.ShopListRepositoryImpl
import com.iruda.cleanshoppinglist.domain.entities.ShopItem
import com.iruda.cleanshoppinglist.domain.usecases.DeleteShopUseCase
import com.iruda.cleanshoppinglist.domain.usecases.GetShopListUseCase
import com.iruda.cleanshoppinglist.domain.usecases.UpdateShopUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ShopListRepositoryImpl(application)

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopUseCase = DeleteShopUseCase(repository)
    private val updateShopUseCase = UpdateShopUseCase(repository)

    private val scope = CoroutineScope(Dispatchers.IO)

    val shopList = getShopListUseCase.getShopList()

    fun deleteShopItem(shopItem: ShopItem) {
        scope.launch {
            deleteShopUseCase.deleteShopItem(shopItem)
        }
    }

    fun changeEnabledStateShopItem(shopItem: ShopItem) {
        scope.launch {
            val newItem = shopItem.copy(isActive = !shopItem.isActive)
            updateShopUseCase.updateShopItem(newItem)
        }
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}