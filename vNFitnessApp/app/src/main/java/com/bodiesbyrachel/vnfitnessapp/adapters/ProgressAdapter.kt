package com.bodiesbyrachel.vnfitnessapp.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bodiesbyrachel.vnfitnessapp.R
import com.bodiesbyrachel.vnfitnessapp.models.ProgressData
import com.bodiesbyrachel.vnfitnessapp.utils.ItemClickListener
import kotlinx.android.synthetic.main.item_progress.view.*

class ProgressAdapter(
    private val activity: Activity,
    private val progressData: List<ProgressData>,
    private val itemClickListener: ItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        if (position == 0)
            return R.layout.item_progress_header
        return R.layout.item_progress
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(activity).inflate(viewType, parent, false)
        if (viewType == R.layout.item_progress_header)
            return ProgressHeaderViewHolder(view)
        return ProgressViewHolder(view)
    }

    override fun getItemCount(): Int {
        return progressData.size + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ProgressHeaderViewHolder)
            holder.bindData()
        else
            (holder as ProgressViewHolder).bindData(progressData[position - 1])

    }

    inner class ProgressHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData() {

        }
    }

    inner class ProgressViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(progressData: ProgressData) {
            itemView.tvProgressTitle.text = progressData.title
        }
    }
}