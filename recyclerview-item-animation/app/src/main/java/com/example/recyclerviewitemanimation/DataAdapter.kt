package com.example.recyclerviewitemanimation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.animation.AnimationUtils
import kotlinx.android.synthetic.main.rowfile.view.*

class DataAdapter(val context: Context, val data: ArrayList<DataModel>) :
    RecyclerView.Adapter<DataAdapter.DataHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rowfile, parent, false)
        return DataHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: DataHolder, position: Int) {

        holder.itemView.ivAuthor.animation =
            android.view.animation.AnimationUtils.loadAnimation(context, R.anim.fade_transition)

        holder.itemView.clMain.animation =
            android.view.animation.AnimationUtils.loadAnimation(context, R.anim.fade_scale)

        holder.bindData(position)
    }

    inner class DataHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(position: Int) {
            Glide.with(context).load(data[position].authorImage).into(itemView.ivAuthor)
            itemView.tvCategory.text = data[position].category
            itemView.tvQuote.text = data[position].quote
            itemView.tvAuthor.text = data[position].authorName

        }
    }

}