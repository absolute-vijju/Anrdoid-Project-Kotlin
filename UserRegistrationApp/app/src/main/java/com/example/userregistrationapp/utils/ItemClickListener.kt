package com.example.userregistrationapp.utils

import androidx.recyclerview.widget.RecyclerView

interface ItemClickListener {
    fun onItemClick(viwHolder: RecyclerView.ViewHolder, position: Int, viewId: Int?)
}