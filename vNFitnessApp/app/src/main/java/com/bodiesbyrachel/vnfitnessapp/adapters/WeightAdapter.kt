package com.bodiesbyrachel.vnfitnessapp.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bodiesbyrachel.vnfitnessapp.R
import com.bodiesbyrachel.vnfitnessapp.models.MealSummary
import com.bodiesbyrachel.vnfitnessapp.utils.Constants
import com.bodiesbyrachel.vnfitnessapp.utils.ItemClickListener
import kotlinx.android.synthetic.main.header.view.*
import kotlinx.android.synthetic.main.item_note.view.*
import kotlinx.android.synthetic.main.item_weight.view.*
import java.io.Serializable

class WeightAdapter(
    private val activity: Activity,
    private val weightType: Serializable?,
    private val weightData: List<MealSummary>,
    private val itemClickListener: ItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        if (position == 0)
            return R.layout.header
        else if (weightData[position - 1].mealName.equals("Note", true))
            return R.layout.item_note
        else return R.layout.item_weight
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(activity).inflate(viewType, parent, false)
        if (viewType == R.layout.header)
            return HeaderViewHolder(view)
        else if (viewType == R.layout.item_note)
            return NoteViewHolder(view)
        else
            return MealViewHolder(view)
    }

    override fun getItemCount(): Int {
        return weightData.size + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HeaderViewHolder)
            holder.bindData()
        else if (holder is MealViewHolder)
            holder.bindData(position - 1)
        else (holder as NoteViewHolder).bindData(position - 1)
    }

    inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        fun bindData() {
            itemView.ivBack.visibility = View.VISIBLE

            if (weightType == Constants.WeightType.GAIN)
                itemView.tvTitle.text = activity.getString(R.string.weight_gain).toUpperCase()
            else
                itemView.tvTitle.text = activity.getString(R.string.weight_loss).toUpperCase()

            itemView.ivBack.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            when (v?.id) {
                R.id.ivBack -> itemClickListener.onItemClick(this, adapterPosition, R.id.ivBack)
            }
        }
    }

    inner class MealViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(position: Int) {

            val mealName = weightData[position].mealName

            if (position != 0)
                itemView.tvMealSummary.visibility = View.GONE

            itemView.tvMeal.text = mealName?.toUpperCase()
            itemView.tvMealDesc.text = weightData[position].desc

            if (mealName!!.equals(activity.getString(R.string.meal_1), true))
                itemView.clMeal.setBackgroundResource(R.drawable.bg_meal_1)
            else if (mealName.equals(activity.getString(R.string.meal_2), true))
                itemView.clMeal.setBackgroundResource(R.drawable.bg_meal_2)
            else if (mealName.equals(activity.getString(R.string.meal_3), true))
                itemView.clMeal.setBackgroundResource(R.drawable.bg_meal_3)
            else if (mealName.equals(activity.getString(R.string.meal_4), true))
                itemView.clMeal.setBackgroundResource(R.drawable.bg_meal_4)

        }
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(position: Int) {
            itemView.tvNoteDesc.text = weightData[position].desc
        }
    }

}