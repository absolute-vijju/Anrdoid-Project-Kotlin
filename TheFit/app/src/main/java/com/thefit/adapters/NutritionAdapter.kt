package com.thefit.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thefit.R
import com.thefit.models.NutritionData
import com.thefit.utils.ItemClickListener
import kotlinx.android.synthetic.main.header.view.*
import kotlinx.android.synthetic.main.header.view.tvTitle
import kotlinx.android.synthetic.main.item_train.view.*


class NutritionAdapter(
    private val activity: Activity,
    private val nutritionList: List<NutritionData>,
    private val itemClickListener: ItemClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> R.layout.header
            1 -> R.layout.item_title
            else -> R.layout.item_train
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(activity).inflate(viewType, parent, false)
        return when (viewType) {
            R.layout.header -> HeaderViewHolder(view)
            R.layout.item_title -> TitleViewHolder(view)
            else -> TrainingViewHolder(view)
        }

    }

    override fun getItemCount(): Int {
        return nutritionList.size + 2
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> holder.bindData()
            is TitleViewHolder -> holder.bindData()
            else -> (holder as TrainingViewHolder).bindData(position - 2)
        }
    }

    inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        fun bindData() {
            itemView.tvTitle.text = activity.getString(R.string.meals).toUpperCase()
            itemView.ivMenu.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            when (v?.id) {
                R.id.ivMenu ->itemClickListener.onItemClick(this,adapterPosition,R.id.ivMenu)
            }
        }
    }

    inner class TitleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData() {
            itemView.tvTitle.text = activity.getString(R.string.get_balance_gurl).toUpperCase()
        }
    }

    inner class TrainingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(position: Int) {
            if (position == 0)
                itemView.vSeparator.visibility = View.VISIBLE
            itemView.tvTitle.text = nutritionList[position].name?.toUpperCase()
        }
    }

}