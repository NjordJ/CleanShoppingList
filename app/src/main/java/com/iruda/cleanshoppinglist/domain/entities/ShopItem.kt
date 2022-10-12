package com.iruda.cleanshoppinglist.domain.entities

data class ShopItem(
    val id: Int,
    val name: String,
    val count: Int,
    val isActive: Boolean
)
