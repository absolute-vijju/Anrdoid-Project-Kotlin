package com.bodiesbyrachel.vnfitnessapp.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.bodiesbyrachel.vnfitnessapp.R
import com.bodiesbyrachel.vnfitnessapp.models.TipsData
import kotlinx.android.synthetic.main.item_tips.view.*

class TipsAdapter(private val activity: Activity, private val tipsData: List<TipsData>) :
    RecyclerView.Adapter<TipsAdapter.SliderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        val view = LayoutInflater.from(activity).inflate(R.layout.item_tips, parent, false)
        return SliderViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tipsData.size
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.bindData(position)
    }

    inner class SliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(position: Int) {
            itemView.tvTipTitle.text = tipsData[position].tipTitle
            itemView.tvTipDesc.text = tipsData[position].tipDesc
        }
    }
}

/*
class TipsAdapter(private val activity: Activity, private val tipsData: List<TipsData>) :
    PagerAdapter() {
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val view = LayoutInflater.from(activity).inflate(R.layout.item_tips, container, false)
        view.tvTipTitle.text = tipsData[position].tipTitle
        view.tvTipDesc.text = tipsData[position].tipDesc

        container.addView(view)

        return view

    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return tipsData.size
    }
}
*/
