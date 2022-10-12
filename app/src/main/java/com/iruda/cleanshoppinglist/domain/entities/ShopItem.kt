package com.iruda.cleanshoppinglist.domain.entities

data class ShopItem(
    val name: String,
    val count: Int,
    val isActive: Boolean,
    var id: Int = UNDEFINED_ID,
) {
    companion object {
        const val UNDEFINED_ID = -1
    }
}
