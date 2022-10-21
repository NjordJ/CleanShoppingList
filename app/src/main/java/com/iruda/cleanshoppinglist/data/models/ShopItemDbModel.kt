package com.iruda.cleanshoppinglist.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_shop_items")
data class ShopItemDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val count: Int,
    val isActive: Boolean,
)