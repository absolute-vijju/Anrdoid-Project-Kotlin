package com.bodiesbyrachel.vnfitnessapp.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bodiesbyrachel.vnfitnessapp.R
import com.bodiesbyrachel.vnfitnessapp.models.Items
import com.bodiesbyrachel.vnfitnessapp.utils.ItemClickListener
import kotlinx.android.synthetic.main.header.view.*
import kotlinx.android.synthetic.main.item_grocery.view.*
import kotlinx.android.synthetic.main.item_grocery_title.view.*

class GroceryAdapter(
    private val activity: Activity,
    private val groceryList: List<Any>,
    private val itemClickListener: ItemClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        if (position == 0)
            return R.layout.header
        else if (groceryList[position - 1] is String)
            return R.layout.item_grocery_title
        else return R.layout.item_grocery
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(activity).inflate(viewType, parent, false)
        if (viewType == R.layout.header)
            return HeaderViewHolder(view)
        else if (viewType == R.layout.item_grocery_title)
            return TitleViewHolder(view)
        else return GroceryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return groceryList.size + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HeaderViewHolder)
            holder.bindData()
        else if (holder is TitleViewHolder)
            holder.bindData(groceryList[position - 1] as String)
        else
            (holder as GroceryViewHolder).bindData(groceryList[position - 1] as Items)
    }

    inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        fun bindData() {
            itemView.ivBack.visibility = View.VISIBLE
            itemView.tvTitle.text = activity.getString(R.string.grocery_list).toUpperCase()

            itemView.ivBack.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            when (v?.id) {
                R.id.ivBack -> itemClickListener.onItemClick(this, adapterPosition, R.id.ivBack)
            }
        }
    }

    inner class TitleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(title: String) {
            itemView.tvGroceryTitle.text = title.toUpperCase()
        }
    }

    inner class GroceryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(items: Items) {
            itemView.tvGroceryName.text = items.itemName?.toUpperCase()
        }
    }

}