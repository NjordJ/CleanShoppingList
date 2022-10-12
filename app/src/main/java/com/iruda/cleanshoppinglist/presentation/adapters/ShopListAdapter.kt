package com.iruda.cleanshoppinglist.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.iruda.cleanshoppinglist.R
import com.iruda.cleanshoppinglist.domain.entities.ShopItem

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
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_shop_enabled, parent, false)
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val item = shopList[position]
        val status = if (item.isActive) "Active" else "Not Active"
        holder.itemView.setOnLongClickListener {
            true
        }
        if (item.isActive) {
            holder.textViewName.text = "${item.name} $status"
            holder.textViewCount.text = item.count.toString()
            holder.textViewName.setTextColor(ContextCompat.getColor(holder.itemView.context, android.R.color.holo_red_light))
        }
    }

    override fun getItemCount() = shopList.size

    override fun onViewRecycled(holder: ShopItemViewHolder) {
        super.onViewRecycled(holder)
        holder.textViewName.text = ""
        holder.textViewCount.text = ""
        holder.textViewName.setTextColor(ContextCompat.getColor(holder.itemView.context, android.R.color.white))
    }

    class ShopItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewName: TextView = view.findViewById(R.id.text_view_name)
        val textViewCount: TextView = view.findViewById(R.id.text_view_count)
    }
}