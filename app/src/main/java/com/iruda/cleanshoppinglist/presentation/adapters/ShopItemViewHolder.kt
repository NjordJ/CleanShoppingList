package com.iruda.cleanshoppinglist.presentation.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.iruda.cleanshoppinglist.R

class ShopItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val textViewName: TextView = view.findViewById(R.id.text_view_name)
    val textViewCount: TextView = view.findViewById(R.id.text_view_count)
}