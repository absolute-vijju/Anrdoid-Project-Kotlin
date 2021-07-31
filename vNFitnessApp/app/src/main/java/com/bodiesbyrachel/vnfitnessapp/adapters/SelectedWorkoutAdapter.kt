package com.bodiesbyrachel.vnfitnessapp.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bodiesbyrachel.vnfitnessapp.R
import com.bodiesbyrachel.vnfitnessapp.models.ExerciseData
import com.bodiesbyrachel.vnfitnessapp.utils.ItemClickListener
import kotlinx.android.synthetic.main.item_day.view.*
import kotlinx.android.synthetic.main.item_selected_workout.view.*

class SelectedWorkoutAdapter(
    private val activity: Activity,
    private val selectedWorkoutList: ArrayList<Any>,
    private val itemClickListener: ItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {

        val obj = selectedWorkoutList[position]
        if (obj is String)
            return R.layout.item_day
        return R.layout.item_selected_workout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(activity).inflate(viewType, parent, false)
        if (viewType == R.layout.item_day)
            return DaysViewHolder(view)
        return SelectedWorkoutViewHolder(view)
    }

    override fun getItemCount(): Int {
        return selectedWorkoutList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is DaysViewHolder)
            holder.bindData(position)
        else
            (holder as SelectedWorkoutViewHolder).bindData(position)
    }

    inner class DaysViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(position: Int) {
            itemView.tvDay.text = selectedWorkoutList[position] as String
        }
    }

    inner class SelectedWorkoutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(position: Int) {
            itemView.setOnClickListener { itemClickListener.onItemClick(this, position, null) }
            val exerciseData = selectedWorkoutList[position] as ExerciseData
            itemView.tvExerciseName.text = exerciseData.exerciseName
        }
    }
}