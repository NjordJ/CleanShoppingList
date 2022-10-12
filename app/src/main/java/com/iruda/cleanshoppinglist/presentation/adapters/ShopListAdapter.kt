package com.iruda.cleanshoppinglist.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.iruda.cleanshoppinglist.R
import com.iruda.cleanshoppinglist.domain.entities.ShopItem
import java.lang.RuntimeException

class ShopListAdapter : RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {

    var shopList = listOf<ShopItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ShopItemViewHolder {
        val layout = when(viewType) {
            VIEW_TYPE_ACTIVE -> R.layout.item_shop_enabled
            VIEW_TYPE_NON_ACTIVE -> R.layout.item_shop_disabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val view = LayoutInflater.from(parent.context)
            .inflate(layout, parent, false)
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val item = shopList[position]
        holder.textViewName.text = item.name
        holder.textViewCount.text = item.count.toString()
        holder.itemView.setOnLongClickListener {
            true
        }
    }

    override fun getItemCount() = shopList.size

    override fun getItemViewType(position: Int): Int {
        val item = shopList[position]
        return if (item.isActive) VIEW_TYPE_ACTIVE else VIEW_TYPE_NON_ACTIVE
    }

    class ShopItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewName: TextView = view.findViewById(R.id.text_view_name)
        val textViewCount: TextView = view.findViewById(R.id.text_view_count)
    }

    companion object {
        const val VIEW_TYPE_ACTIVE = 100
        const val VIEW_TYPE_NON_ACTIVE = 101

        const val MAX_POOL_SIZE = 25
    }
}