package com.bodiesbyrachel.vnfitnessapp.utils

import androidx.recyclerview.widget.RecyclerView

interface ItemClickListener {
    fun onItemClick(viewHolder: RecyclerView.ViewHolder, position: Int, viewId: Int?)
}