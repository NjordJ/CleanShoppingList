package com.iruda.cleanshoppinglist.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.iruda.cleanshoppinglist.data.repositories.ShopListRepositoryImpl
import com.iruda.cleanshoppinglist.domain.entities.ShopItem
import com.iruda.cleanshoppinglist.domain.usecases.AddShopUseCase
import com.iruda.cleanshoppinglist.domain.usecases.GetShopItemUseCase
import com.iruda.cleanshoppinglist.domain.usecases.UpdateShopUseCase
import kotlinx.coroutines.launch

class ShopItemViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ShopListRepositoryImpl(application)

    private val getShopItemUseCase = GetShopItemUseCase(repository)
    private val addShopUseCase = AddShopUseCase(repository)
    private val updateShopUseCase = UpdateShopUseCase(repository)

    // Set values private and allow access from view only with getter
    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _errorInputCount = MutableLiveData<Boolean>()
    val errorInputCount: LiveData<Boolean>
        get() = _errorInputCount

    private val _shopItem = MutableLiveData<ShopItem>()
    val shopItem: LiveData<ShopItem>
        get() = _shopItem

    private val _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit>
        get() = _shouldCloseScreen

    fun getShopItem(shopItemId: Int) {
        viewModelScope.launch {
            val item = getShopItemUseCase.getShopItem(shopItemId)
            _shopItem.postValue(item)
        }
    }

    fun addShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInputs(name, count)
        if (fieldsValid) {
            viewModelScope.launch {
                val shopItem = ShopItem(name, count, true)
                addShopUseCase.addShopItem(shopItem)
            }
            finishWork()
        }
    }

    fun updateShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInputs(name, count)
        if (fieldsValid) {
            _shopItem.value?.let {
                viewModelScope.launch {
                    val item = it.copy(name = name, count = count)
                    updateShopUseCase.updateShopItem(item)
                }
                finishWork()
            }
        }
    }

    private fun parseName(inputName: String?) = inputName?.trim() ?: ""

    private fun parseCount(inputName: String?): Int {
        return try {
            inputName?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    private fun validateInputs(name: String, count: Int): Boolean {
        var result = true
        if (name.isBlank()) {
            _errorInputName.postValue(true)
            result = false
        }
        if (count <= 0) {
            _errorInputCount.postValue(true)
            result = false
        }
        return result
    }

    fun resetErrorInputName() = _errorInputName.postValue(false)

    fun resetErrorInputCount() = _errorInputCount.postValue(false)

    private fun finishWork() {
        _shouldCloseScreen.postValue(Unit)
    }

}